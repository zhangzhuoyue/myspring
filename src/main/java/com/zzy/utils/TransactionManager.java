package com.zzy.utils;

import com.zzy.annocation.Autowired;
import com.zzy.annocation.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangyue666
 * @description 事务管理器  ：负责事务的 开启 提交 关闭
 * @date 2020/12/26 14:38
 */
@Service
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启手动事务控制
     */

    public void beginTransaction() throws SQLException {
        Connection currentThreadConn = connectionUtils.getCurrentThreadConn();
        currentThreadConn.setAutoCommit(false);
    }

    //提交事务
    public void commit() throws SQLException {
        Connection currentThreadConn = connectionUtils.getCurrentThreadConn();
        currentThreadConn.commit();
    }

    //回滚事务
    public void rollback() throws SQLException {
        Connection currentThreadConn = connectionUtils.getCurrentThreadConn();
        currentThreadConn.rollback();
    }
}
