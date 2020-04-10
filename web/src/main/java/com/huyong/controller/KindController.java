package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.module.KindBO;
import com.huyong.service.KindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("更新或者添加")
    @PostMapping("/updateOrInsert")
    @CheckAuth
    @ResponseBody
    public void updateOrInsert(@RequestBody @ValidationParam("kindName") KindBO kindBO) {
        kindService.updateOrInsert(kindBO);
    }
    @ResponseBody
    @ApiOperation("删除")
    @PostMapping("/remove")
    public void remove(@RequestBody Map<String, List<Long>> map) {
        kindService.remove(map);
    }

}