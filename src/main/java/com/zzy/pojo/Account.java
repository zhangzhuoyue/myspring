package com.zzy.pojo;

/**
 * @author zhangyue666
 * @description 实体类
 * @date 2020/12/22 21:38
 */
public class Account {
    private String cardNo;
    private String name;
    private int money;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String carNo) {
        this.cardNo = carNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
