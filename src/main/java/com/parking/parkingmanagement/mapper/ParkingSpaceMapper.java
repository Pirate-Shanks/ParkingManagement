package com.parking.parkingmanagement.mapper;

import com.parking.parkingmanagement.entity.ParkingSpace;
import com.parking.parkingmanagement.entity.ParkingSpaceExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParkingSpaceMapper {
    long countByExample(ParkingSpaceExample example);

    int deleteByExample(ParkingSpaceExample example);

    int deleteByPrimaryKey(String id);

    int insert(ParkingSpace record);

    int insertSelective(ParkingSpace record);

    List<ParkingSpace> selectByExample(ParkingSpaceExample example);

    ParkingSpace selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParkingSpace record, @Param("example") ParkingSpaceExample example);

    int updateByExample(@Param("record") ParkingSpace record, @Param("example") ParkingSpaceExample example);

    int updateByPrimaryKeySelective(ParkingSpace record);

    int updateByPrimaryKey(ParkingSpace record);
}