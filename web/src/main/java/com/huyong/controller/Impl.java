package com.huyong.controller;

import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 描述: r
 *
 * @author huyong
 * @date 2020-03-14 10:41 下午
 */
@Component
public class Impl implements Interf {
    @Override
    public void say() {
        System.out.println("hello world");
    }


}
