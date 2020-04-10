package com.huyong.service;

import com.huyong.constant.CommonConstant;
import com.huyong.dao.entity.FileDO;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.module.FileBO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.FileRefEnum;
import com.huyong.enums.FileTypeEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.FileMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: FileService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class FileService {
    @Resource
    private FileMapper fileMapper;
    @Resource
    private UserMapper userMapper;

    public FileDO convertBo2Do(FileBO file) {
        FileDO fileDO = new FileDO();
        BeanUtils.copyProperties(file, fileDO);
        return fileDO;
    }
    public List<FileDO> convertBos2Dos(List<FileBO> files) {
        if (null == files || files.isEmpty()) {
            return new ArrayList<>();
        }
        return files.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public FileBO convertDo2Bo(FileDO file) {
        FileBO fileBO = new FileBO();
        BeanUtils.copyProperties(file, fileBO);
        return fileBO;
    }
    public List<FileBO> convertDos2Bos(List<FileDO> files) {
        if (null == files || files.isEmpty()) {
            return new ArrayList<>();
        }
        return files.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadBar(MultipartFile file) {
        //命名为id + 事件戳
        String fileName = AuthUtils.getUser().getId() + "+" + System.currentTimeMillis() + ".jpg";
        upload(file, fileName);
        //更改用户头像
        UserDO condition = new UserDO();
        condition.setId(AuthUtils.getUser().getId());
        condition.setPicture(CommonConstant.VISIT_FILE_LOCATION + fileName);
        userMapper.updateByPrimary(condition);
    }

    /**
     * 管理员上传图片
     * @param file
     * @return
     */
    public String uploadImg(MultipartFile file) {
        String fileName = System.currentTimeMillis() + ".jpg";
        upload(file, fileName);
        return CommonConstant.VISIT_FILE_LOCATION + fileName;
    }

    private void upload(MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            throw new CommonException("上传文件为空！");
        }
        File dest = new File(CommonConstant.FILE_LOCATION + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new CommonException("上传失败！");
        }
        //存储上传文件日志
        FileDO log = new FileDO();
        log.setRef(FileRefEnum.BAR.getCode());
        log.setType(FileTypeEnum.JPG.getCode());
        log.setUrl(CommonConstant.VISIT_FILE_LOCATION + fileName);
        log.setRefId(AuthUtils.getUser().getId());
        fileMapper.insert(log);
    }
}