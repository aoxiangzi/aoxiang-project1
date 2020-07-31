package com.zairui.project1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zairui.project1.mapper")
public class RestoreSystemApplication{
	public static void main(String[] args){
		SpringApplication.run(RestoreSystemApplication.class, args);
	}
}
