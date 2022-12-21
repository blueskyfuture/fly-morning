package com.bluesky.tech.reflect;

//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * https://www.jianshu.com/p/95eaae0a363f
 */
public class TestCGLibDynamicProxy {

//    public static void main(String[] args) {
//        IRegisterService iRegisterService = new RegisterServiceImpl();
//        InsertDataInterceptor interceptor = new InsertDataInterceptor();
//        RegisterServiceImpl proxy = (RegisterServiceImpl) interceptor.getProxy(iRegisterService);
//        proxy.register("RyanLee", "123");
//    }
//}
//
//class InsertDataInterceptor implements MethodInterceptor {
//    Object target;
//
//    public Object getProxy(Object target) {
//        this.target = target;
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(this.target.getClass());
//        // 回调方法
//        enhancer.setCallback(this);
//        // 创建代理对象
//        return enhancer.create();
//    }
//
//    private void doBefore() {
//        System.out.println("[Proxy]一些前置处理");
//    }
//
//    private void doAfter() {
//        System.out.println("[Proxy]一些后置处理");
//    }
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        doBefore();
//        Object result = methodProxy.invoke(target, objects);
//        doAfter();
//        return result;
//    }
}

