package com.parking.parkingmanagement.exception;

public enum BusinessExceptionCode {

    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    UNAUTHORIZED("身份认证无效");

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
