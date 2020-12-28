package com.zzy.factory;

import com.zzy.annocation.Autowired;
import com.zzy.annocation.Service;
import com.zzy.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @author zhangyue666
 * @description 代理类
 * @date 2020/12/26 19:07
 */
@Service(value = "proxyFactory")
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    //反射初始化属性
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * jdk动态代理
     */
    public Object getjdkproxy(Object obj) {
        //获取代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    //开启事务（手动提交事务）
                    transactionManager.beginTransaction();
                    result = method.invoke(obj, args);
                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    //事务回滚
                    transactionManager.rollback();
                    //抛出异常便于上层servlet捕获
                    throw e;
                }
                return result;
            }
        });
    }

    /**
     * 使用cglib动态代理生成代理对象
     */
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try {
                    transactionManager.beginTransaction();
                    result = method.invoke(obj, objects);
                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    //回滚事务
                    transactionManager.rollback();
                    //抛出异常，上层servlet捕获
                    throw e;
                }
                return result;
            }
        });
    }

}
