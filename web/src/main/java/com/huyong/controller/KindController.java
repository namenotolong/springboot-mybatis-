package com.huyong.controller;

import com.huyong.dao.module.KindBO;
import com.huyong.service.KindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: KindController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/kind")
@Api("分类管理")
public class KindController {

    @Resource
    private KindService kindService;

    @ResponseBody
    @PostMapping("/add")
    @ApiOperation("添加分类")
    public void add() {
        kindService.add();
    }

    @GetMapping("/getKinds")
    @ResponseBody
    @ApiOperation("获取目录")
    public List<KindBO> getKinds() {
        return kindService.getKinds();
    }
}