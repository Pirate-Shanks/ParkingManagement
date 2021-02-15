package com.parking.parkingmanagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parking.parkingmanagement.entity.ParkingSpace;
import com.parking.parkingmanagement.entity.ParkingSpaceExample;
import com.parking.parkingmanagement.enums.ParkingSpaceStatus;
import com.parking.parkingmanagement.mapper.ParkingSpaceMapper;
import com.parking.parkingmanagement.utils.CopyUtil;
import com.parking.parkingmanagement.utils.UuidUtil;
import com.parking.parkingmanagement.vo.PageVO;
import com.parking.parkingmanagement.vo.ParkingSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpaceService {
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    public void list(PageVO pageVO){
        PageHelper.startPage(pageVO.getPage(),pageVO.getSize());
        ParkingSpaceExample parkingSpaceExample = new ParkingSpaceExample();
        List<ParkingSpace> ParkingSpaceList = parkingSpaceMapper.selectByExample(parkingSpaceExample);
        PageInfo<ParkingSpace> pageInfo = new PageInfo<>(ParkingSpaceList);
        pageVO.setTotal(pageInfo.getTotal());
        List<ParkingSpaceVO> ParkingSpaceDtoList = copyParkingSpaceVOList(ParkingSpaceList);
        pageVO.setList(ParkingSpaceDtoList);
    }

    public void save(ParkingSpaceVO parkingSpaceVO) {
        ParkingSpace ParkingSpace = CopyUtil.copy(parkingSpaceVO, ParkingSpace.class);
        if(StringUtils.isEmpty(parkingSpaceVO.getId())){
            insert(ParkingSpace);
        }else {
            update(ParkingSpace);
        }
    }

    private void insert(ParkingSpace parkingSpace) {
        parkingSpace.setId(UuidUtil.getShortUuid());
        parkingSpaceMapper.insert(parkingSpace);
    }

    private void update(ParkingSpace parkingSpace) {
        parkingSpaceMapper.updateByPrimaryKey(parkingSpace);
    }

    public void delete(String id) {
        parkingSpaceMapper.deleteByPrimaryKey(id);
    }

    private List<ParkingSpaceVO> copyParkingSpaceVOList(List<ParkingSpace> parkingSpaceList) {
        List<ParkingSpaceVO> parkingSpaceVOList = new ArrayList<>();
        for (ParkingSpace parkingSpace:parkingSpaceList) {
            ParkingSpaceVO parkingSpaceVO = CopyUtil.copy(parkingSpace, ParkingSpaceVO.class);
            if(parkingSpace.getParkStatus() == 0) {
                parkingSpaceVO.setStatusObject(ParkingSpaceStatus.EMPTY);
            }else if(parkingSpace.getParkStatus() == 1) {
                parkingSpaceVO.setStatusObject(ParkingSpaceStatus.BUSY);
            }else if(parkingSpace.getParkStatus() == 2) {
                parkingSpaceVO.setStatusObject(ParkingSpaceStatus.RESERVE);
            }
            parkingSpaceVOList.add(parkingSpaceVO);
        }
        return parkingSpaceVOList;
    }
}
