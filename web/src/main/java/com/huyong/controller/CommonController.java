package com.huyong.controller;

import com.huyong.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 描述: common
 *
 * @author huyong
 * @date 2020-04-07 10:07 下午
 */
@Controller
@Api("common")
public class CommonController {
    @Resource
    private CommonService commonService;
    @GetMapping("/baseTable")
    @ApiOperation("获取基础表格枚举数据")
    @ResponseBody
    public Map<String, Map<Integer, String>> getBaseTable() {
        return commonService.getBaseTable();
    }
}
