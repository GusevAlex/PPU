package com.PPU.DB;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 20.05.14
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class BeforeMethod implements org.springframework.aop.MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("com.PPU.DB.BeforeMethod : До метода!");
    }

}
