package com.nku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nku.*.mapper")
public class Cet6Application {
    public static void main(String[] args) {
        SpringApplication.run(Cet6Application.class, args);
    }
}
