package com.huyong.service;

import com.google.common.collect.Maps;
import com.huyong.enums.GenderEnum;
import com.huyong.enums.OnlineEnum;
import com.huyong.enums.RoleEnum;
import com.huyong.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述: common service
 *
 * @author huyong
 * @date 2020-04-07 10:08 下午
 */
@Service
public class CommonService {
    /**
     * 获取基础表格枚举数据
     * @return
     */
    public Map<String, Map<Integer, String>> getBaseTable() {
        Map<String, Map<Integer, String>> maps = Maps.newHashMap();
        //online枚举
        Map<Integer, String> map = Maps.newHashMap();
        final OnlineEnum[] values = OnlineEnum.values();
        for (OnlineEnum value : values) {
            map.put(value.getCode(), value.getDesc());
        }
        maps.put("online", map);
        //gender枚举
        Map<Integer, String> gender = Maps.newHashMap();
        final GenderEnum[] genders = GenderEnum.values();
        for (GenderEnum value : genders) {
            gender.put(value.getCode(), value.getDesc());
        }
        maps.put("gender", gender);
        //role枚举
        Map<Integer, String> role = Maps.newHashMap();
        final RoleEnum[] roles = RoleEnum.values();
        for (RoleEnum value : roles) {
            role.put(value.getCode(), value.getDesc());
        }
        maps.put("role", role);
        return maps;
    }

    public static void main(String[] args) {
        final String s = HttpUtil.get("https://element.eleme.cn/#/zh-CN/component/icon");
        System.out.println(s);
    }
}
