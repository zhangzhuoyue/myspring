package com.zzy.service;

import com.zzy.annocation.Autowired;
import com.zzy.annocation.Service;
import com.zzy.annocation.Transactional;
import com.zzy.dao.AccountDao;
import com.zzy.pojo.Account;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/26 21:00
 */
@Service(value = "transferService")
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        try{
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);
            accountDao.updateAccountByCardNo(to);

            //测试事务回滚
            int c = 1/0;
            accountDao.updateAccountByCardNo(from);

        }catch (Exception e) {
            e.printStackTrace();
            // 抛出异常便于上层servlet捕获
            throw e;
        }
    }
}
