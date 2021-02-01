package com.parking.parkingmanagement.vo;


import lombok.Data;

import java.util.HashSet;
import java.util.List;
@Data
public class LoginUserVO {

    /**
     * id
     */
    private String id;

    /**
     * 登陆名
     */
    private String userName;

    /**
     * 登录凭证
     */
    private String token;

    /**
     * 所有资源，用于前端界面控制，预留，不管
     */
    private List<Object> resources;

    /**
     * 所有资源中的请求，用于后端接口拦截,暂时先不管
     */
    private HashSet<String> requests;


}