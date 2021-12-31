package com.acg.harmonica;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.acg.harmonica.dao")

public class HarmonicaCentreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarmonicaCentreApplication.class, args);
    }

}
