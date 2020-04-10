package com.huyong.controller;

import com.huyong.service.DashboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 描述: dashboard首页
 *
 * @author huyong
 * @date 2020-04-05 4:29 下午
 */
@Controller
@RequestMapping("/dashboard")
@Api("首页表格")
public class DashboardController {
    @Resource
    private DashboardService dashboardService;
    @GetMapping("/grid")
    @ResponseBody
    @ApiOperation("获取数量")
    public List<Long> getGrid() {
        return dashboardService.getGrid();
    }

    @GetMapping("/lineChart")
    @ResponseBody
    @ApiOperation("获取月、周的折线图的数据")
    public Map<String, List<Map<String, Long>>> lineChart(@ApiParam("week month") @RequestParam("type") String type) {
        return dashboardService.lineChart(type);
    }
}
