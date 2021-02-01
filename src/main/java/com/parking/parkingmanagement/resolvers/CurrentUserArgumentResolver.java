package com.parking.parkingmanagement.resolvers;

import com.alibaba.fastjson.JSON;
import com.parking.parkingmanagement.annotation.CurrentUser;
import com.parking.parkingmanagement.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberVO.class)&& //判断是否能转换成MemberVO
                parameter.hasParameterAnnotation(CurrentUser.class);//判断是否有currentUser注解
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String json = redisTemplate.opsForValue().get(nativeWebRequest.getHeader("token"));
        MemberVO memberVO = JSON.parseObject(json,MemberVO.class);
        return memberVO;
    }
}
