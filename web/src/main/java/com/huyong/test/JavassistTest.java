package com.huyong.test;

import javassist.*;

import java.io.IOException;

/**
 * 描述: javassistTest
 *
 * @author huyong
 * @date 2020-03-17 6:30 下午
 */
public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.huyong.test.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class<?> c = cc.toClass();
        cc.writeFile(String.valueOf(Thread.currentThread().getContextClassLoader().getResource(Base.class.getPackage().getName())));
        Base h = (Base)c.newInstance();
        h.process();
    }
}