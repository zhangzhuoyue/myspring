package com.zzy.dao;

import com.zzy.pojo.Account;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/26 14:40
 */
public interface AccountDao {
    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;

}
