package com.parking.parkingmanagement.controller;

import com.parking.parkingmanagement.exception.BusinessException;
import com.parking.parkingmanagement.exception.ValidatorException;
import com.parking.parkingmanagement.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseVO validatorExceptionHandler(ValidatorException e) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setSuccess(false);
        LOG.warn(e.getMessage());
        responseVO.setMessage("请求参数异常！");
        return responseVO;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseVO businessExceptionHandler(BusinessException e) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setSuccess(false);
        LOG.error("业务异常：{}", e.getCode().getDesc());
        responseVO.setMessage(e.getCode().getDesc());
        return responseVO;
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO businessExceptionHandler(Exception e) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setSuccess(false);
        LOG.error("业务异常：{}", e.getMessage());
        responseVO.setMessage("服务异常");
        responseVO.setCode("500");
        return responseVO;
    }
}
