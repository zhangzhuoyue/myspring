package com.zzy.utils;

import com.zzy.annocation.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangyue666
 * @descriptionb 连接工具
 * @date 2020/12/26 14:41
 */
@Service
public class ConnectionUtils {
    /**
     * 存储当前线程连接
     */
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    /**
     * 从当前线程获取链接
     */
    public Connection getCurrentThreadConn() throws SQLException {
        /**
         * 判断当前线程如果没有绑定连接就从连接池中绑定一个链接
         */
        Connection connection = threadLocal.get();
        if (connection == null) {
            //从连接池拿到连接绑定到线程
            connection = DruidUtils.getDruidDataSource().getConnection();
            //绑定到当前线程
            threadLocal.set(connection);
        }
        return connection;
    }
}
