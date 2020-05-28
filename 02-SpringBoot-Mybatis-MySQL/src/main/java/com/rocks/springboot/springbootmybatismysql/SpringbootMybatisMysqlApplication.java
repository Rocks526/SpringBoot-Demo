package com.rocks.springboot.springbootmybatismysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rocks.springboot.springbootmybatismysql.dao")
public class SpringbootMybatisMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisMysqlApplication.class, args);
    }

}
