package com.huyong.controller;

import com.huyong.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: FileController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/FileController")
public class FileController {

    @Resource
    private FileService fileService;
}