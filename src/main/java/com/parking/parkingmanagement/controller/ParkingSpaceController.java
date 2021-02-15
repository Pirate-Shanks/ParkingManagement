package com.parking.parkingmanagement.controller;

import com.parking.parkingmanagement.service.ParkingSpaceService;
import com.parking.parkingmanagement.vo.PageVO;
import com.parking.parkingmanagement.vo.ParkingSpaceVO;
import com.parking.parkingmanagement.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingSpace")
public class ParkingSpaceController {

    @Autowired
    ParkingSpaceService parkingSpaceService;

    private static final String BUSINESS_NAME = "停车位管理";

    @PostMapping("/list")
    public ResponseVO list(@RequestBody PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        parkingSpaceService.list(pageVO);
        responseVO.setContent(pageVO);
        return responseVO;
    }

    @PostMapping("/save")
    public ResponseVO save(@RequestBody ParkingSpaceVO parkingSpaceVO){
        ResponseVO responseVO = new ResponseVO();
        parkingSpaceService.save(parkingSpaceVO);
        responseVO.setContent(parkingSpaceVO);
        return responseVO;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseVO delete(@PathVariable String id){
        ResponseVO responseVO = new ResponseVO();
        parkingSpaceService.delete(id);
        return responseVO;
    }
}
