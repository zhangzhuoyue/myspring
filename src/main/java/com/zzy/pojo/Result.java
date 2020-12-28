package com.zzy.pojo;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/26 20:53
 */
public class Result {
    private String code;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
