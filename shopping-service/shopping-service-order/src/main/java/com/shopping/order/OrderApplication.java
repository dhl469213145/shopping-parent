package com.shopping.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import utils.IdWorker;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
//@MapperScan("com.shopping.goods.mapper")
//@MapperScan(basePackages = "classpath*:/mapper/**/*.xml")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1) ;
    }
}
