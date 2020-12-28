package com.zzy.service;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/26 20:59
 */
public interface TransferService {
    void transfer(String fromCardNo, String toCardNo, int money) throws Exception;
}
