package com.parking.parkingmanagement.enums;

public enum ParkingSpaceStatus {
    EMPTY(0, "空闲"),
    BUSY(1, "使用中"),
    RESERVE(2, "预约未使用");

    private Integer code;

    private String desc;

    ParkingSpaceStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
