package com.ysl.bannerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ysl.commonservice.dao")
@ComponentScan(basePackages = "com.ysl.*")
@EntityScan("com.ysl.commonservice.*")
public class BannerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BannerServiceApplication.class, args);
    }

}
