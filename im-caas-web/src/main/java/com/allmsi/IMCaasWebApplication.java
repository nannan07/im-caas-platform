package com.allmsi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.allmsi.**.dao")
public class IMCaasWebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(IMCaasWebApplication.class, args);
	}
}