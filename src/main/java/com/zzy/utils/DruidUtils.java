package com.zzy.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author zhangyue666
 * @description 数据库连接工具
 * @date 2020/12/22 22:09
 */
public class DruidUtils {
    private DruidUtils() {
    }

    //设置全局数据源
    private static DruidDataSource druidDataSource = new DruidDataSource();

    //数据库连接初始化
    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       // druidDataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        druidDataSource.setUrl("jdbc:mysql://192.168.249.157:3306/mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
    }

    public static DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }


}
