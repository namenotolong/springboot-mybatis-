package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.KindMapper;

/**
 * 描述: KindService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class KindService {
    @Resource
    private KindMapper kindMapper;
}