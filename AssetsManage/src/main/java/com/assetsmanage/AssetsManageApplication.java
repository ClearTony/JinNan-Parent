package com.assetsmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.assetsmanage.mapper")
public class AssetsManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetsManageApplication.class, args);
    }

}
