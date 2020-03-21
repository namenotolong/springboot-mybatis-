package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 描述: FileController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/file")
@Api(tags = "文件")
public class FileController {

    @Resource
    private FileService fileService;

    @ResponseBody
    @PostMapping("/uploadBar")
    @ApiOperation("文件上传")
    @CheckAuth
    public void uploadBar(@ApiParam("用户上传头像") @RequestParam("file") MultipartFile file) {
        fileService.uploadBar(file);
    }
}