package com.parking.parkingmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.parking.parkingmanagement.annotation.CurrentUser;
import com.parking.parkingmanagement.service.UserService;
import com.parking.parkingmanagement.utils.UuidUtil;
import com.parking.parkingmanagement.vo.LoginUserVO;
import com.parking.parkingmanagement.vo.MemberVO;
import com.parking.parkingmanagement.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    UserService userService;

    @RequestMapping("test")
    public String hello(){
        return "hello 李冉";
    }

    @RequestMapping("testtoken")
    public MemberVO test(@CurrentUser MemberVO memberVO){
        return memberVO;
    }

    @RequestMapping("/login")
    public ResponseVO login(@RequestBody MemberVO memberVO){
        LOG.info("用户登录开始");
        ResponseVO responseVO = new ResponseVO();
        LoginUserVO loginUserVO = userService.login(memberVO);
        String token = UuidUtil.getShortUuid();
        loginUserVO.setToken(token);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserVO), 3600, TimeUnit.SECONDS);
        responseVO.setContent(loginUserVO);
        return responseVO;
    }
    /**
     * 退出登录
     */
    @GetMapping("/logout/{token}")
    public ResponseVO logout(@PathVariable String token) {
        ResponseVO responseVO = new ResponseVO();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token:{}", token);
        return responseVO;
    }

    @GetMapping("/updateToken/{token}")
    public ResponseVO updateToken(@PathVariable String token) {
        ResponseVO responseVO = new ResponseVO();
        LOG.info("从redis中更新token:{}", token);
        String newToken = UuidUtil.getShortUuid();
        String tokenJson = redisTemplate.opsForValue().get(token);
        redisTemplate.opsForValue().set(newToken, tokenJson, 3600, TimeUnit.SECONDS);
        responseVO.setContent(newToken);
        return responseVO;
    }
}
