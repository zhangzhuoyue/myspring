package com.zzy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author zhangyue666
 * @description json工具，使用jackson实现
 * @date 2020/12/26 14:26
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换为json
     */

    public static String object2Json(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将json转化为对象
     */
    public static <T> T json2Pojo(String jsonData ,Class<T> beanType){
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转换为pojo的list
     */
    public static <T> List<T> json2List(String jsonData,Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> value = MAPPER.readValue(jsonData, javaType);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
