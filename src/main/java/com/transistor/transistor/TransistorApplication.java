package com.transistor.transistor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableJpaAuditing
public class TransistorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransistorApplication.class, args);
    }

}
