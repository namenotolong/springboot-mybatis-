package com.huyong.service;

import com.huyong.dao.entity.FileDO;
import com.huyong.dao.module.FileBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.FileMapper;

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
}