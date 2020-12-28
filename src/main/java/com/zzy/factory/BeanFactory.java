package com.zzy.factory;

import com.zzy.annocation.Autowired;
import com.zzy.annocation.Service;
import com.zzy.annocation.Transactional;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyue666
 * @description 对象工厂
 * @date 2020/12/26 17:46
 */
public class BeanFactory {
    //存储对象
    private static Map<String, Object> map = new HashMap<>();

    static {
        try {
            //扫描包，获取反射对象集合
            Reflections re = new Reflections("com.zzy");

            //获取注解为service对象集合
            Set<Class<?>> serviceSet = re.getTypesAnnotatedWith(Service.class);

            for (Class<?> service : serviceSet) {
                //通过反射技术实例化对象
                Object instance = service.newInstance();
                Service serviceAnnotation = service.getAnnotation(Service.class);

                //对象ID在Service注解有value时用value值，没有使用类名
                if (StringUtils.isEmpty(serviceAnnotation.value())) {
                    //getName获取的是全限定名，所以分割前面包名
                    String[] serviceName = service.getName().split("\\.");
                    map.put(serviceName[serviceName.length - 1], instance);
                } else {
                    map.put(serviceAnnotation.value(), instance);
                }
            }

            //实例化完成，维护对象的依赖关系
            for (Map.Entry<String, Object> amap : map.entrySet()) {
                Object obj = amap.getValue();
                Class<?> aClass = obj.getClass();
                //获取属性集合
                Field[] fields = aClass.getDeclaredFields();
                //遍历属性，如果有autowired注解则注入
                for (Field field : fields) {
                    //boolean value = field.getAnnotation(Autowired.class).value();
                    if (field.isAnnotationPresent(Autowired.class) && field.getAnnotation(Autowired.class).value()) {
                        String[] names = field.getType().getName().split("\\.");
                        String name = names[names.length - 1];
                        Method[] methods = aClass.getMethods();
                        for (int i = 0; i < methods.length; i++) {
                            Method method = methods[i];
                            //该方法是 setAccountDao（AccountDao dao）
                            if (method.getName().equalsIgnoreCase("set" + name)) {
                                method.invoke(obj, map.get(name));
                            }
                        }
                    }
                }
            }

            //判断对象是否持有taansactional注解 ， 若有则修改对象为代理对象
            for (Map.Entry<String, Object> amap : map.entrySet()) {
                Object obj = amap.getValue();
                Class<?> aClass = obj.getClass();
                if (aClass.isAnnotationPresent(Transactional.class)) {
                    //获取代理工厂
                    ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
                    //判断对象是否实现接口
                    //获取类service实现的所有接口
                    Class<?>[] classes = aClass.getInterfaces();
                    if (classes != null && classes.length > 0) {
                        //实现接口使用jdk
                        obj = proxyFactory.getjdkproxy(obj);
                    } else {
                        //没实现使用cglib
                        obj = proxyFactory.getCglibProxy(obj);
                    }
                    //TODO 把处理之后的object重新放到map中,放错位置
                    //map.put(amap.getKey(), obj);
                }
                //把处理之后的object重新放到map中
                map.put(amap.getKey(), obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 对外提供获取实例对象接口 根据id获取
     */
    public static Object getBean(String id) {
        return map.get(id);
    }

}
