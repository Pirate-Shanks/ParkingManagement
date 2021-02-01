package com.parking.parkingmanagement.interceptors;

import com.parking.parkingmanagement.exception.BusinessException;
import com.parking.parkingmanagement.exception.BusinessExceptionCode;
import com.parking.parkingmanagement.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SysInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SysInterceptor.class);
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.debug("SysInterceptor preHandle");
        String token = request.getHeader("token");
        LOG.info("登陆拦截，token: {}",token);
        if (!StringUtils.isEmpty(token)) {
            String jsonToken = redisTemplate.opsForValue().get(token).toString();
            if(StringUtils.isEmpty(jsonToken)) {
                throw new BusinessException(BusinessExceptionCode.UNAUTHORIZED);
            }else {
                return true;
            }
        }
        response.setStatus(401);
        return false;
    }
}