package com.huyong.controller;

import com.huyong.dao.entity.BarDO;
import com.huyong.dao.module.BarBO;
import com.huyong.service.BarService;
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
 * 描述: BarController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/bar")
@Api("导航菜单相关接口")
public class BarController {

    @Resource
    private BarService barService;
    @ApiOperation("test")
    @GetMapping("/test")
    @ResponseBody
    public void test() {
        barService.test();
    }
    @ApiOperation("获取所有标签")
    @GetMapping("/getAll")
    @ResponseBody
    public List<BarDO> getAll() {
        return barService.getAll();
    }


}