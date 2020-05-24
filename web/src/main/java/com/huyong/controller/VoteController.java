package com.huyong.controller;

import com.google.common.collect.Lists;
import com.huyong.dao.entity.UserVoteDO;
import com.huyong.dao.entity.VoteDO;
import com.huyong.dao.mapper.UserVoteMapper;
import com.huyong.dao.mapper.VoteMapper;
import com.huyong.dao.module.VoteBO;
import com.huyong.exception.CommonException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: VoteController
 *
 * @author yangtao
 * @date 2020-05-17 21:07
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    @Resource
    private VoteMapper voteMapper;
    @Resource
    private UserVoteMapper userVoteMapper;

    private static final Long ID = 1L;
    @ResponseBody
    @GetMapping("/getVotes")
    public List<VoteBO> getVotes() {
        final List<VoteDO> votes = voteMapper.queryByCondition(null);
        final UserVoteDO con = new UserVoteDO();
        con.setUserId(ID);
        final List<UserVoteDO> userVotes = userVoteMapper.queryByCondition(con);
        List<VoteBO> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(votes)) {
            result = votes.stream().map(e -> {
                final VoteBO voteBO = new VoteBO();
                BeanUtils.copyProperties(e, voteBO);
                voteBO.setIsVote(false);
                return voteBO;
            }).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(userVotes)) {
                return result;
            }
            final List<Long> ids = userVotes.stream().map(UserVoteDO::getVoteId).collect(Collectors.toList());
            for (VoteBO voteBO : result) {
                if (ids.contains(voteBO.getId())) {
                    voteBO.setIsVote(true);
                }
            }
        }
        return result;
    }

    @ResponseBody
    @GetMapping("/handle")
    public void handle(@RequestParam("id") Long id) {
        if (id == null) {
            throw new CommonException("投票的id为空");
        }
        final UserVoteDO userVoteDO = new UserVoteDO();
        userVoteDO.setUserId(ID);
        userVoteDO.setVoteId(id);
        final List<UserVoteDO> userVoteDOS = userVoteMapper.queryByCondition(userVoteDO);
        if (CollectionUtils.isNotEmpty(userVoteDOS)) {
            throw new CommonException("您已经投过票了！");
        }
        final int insert = userVoteMapper.insert(userVoteDO);
        if (insert < 1) {
            throw new CommonException("系统异常投票失败！");
        }
        final VoteDO voteDO = voteMapper.selectByPrimary(id);
        voteDO.setTickets(voteDO.getTickets() + 1);
        voteMapper.updateByPrimary(voteDO);
    }

}
