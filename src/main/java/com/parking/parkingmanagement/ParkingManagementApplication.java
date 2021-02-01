package com.parking.parkingmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.course.server.mapper")
public class ParkingManagementApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ParkingManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ParkingManagementApplication.class);
		ConfigurableEnvironment environment = app.run(args).getEnvironment();
		LOG.info("启动成功");
		LOG.info("ParkingManagementApplication地址: \t http://127.0.0.1:{}",environment.getProperty("server.port"));
	}

}
