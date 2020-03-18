package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.FileMapper;

/**
 * 描述: FileService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class FileService {
    @Resource
    private FileMapper fileMapper;
}