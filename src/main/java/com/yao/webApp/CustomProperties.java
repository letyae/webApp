package com.yao.webApp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@ConfigurationProperties(prefix= "com.yao.webapp")
@Data
public class CustomProperties {
   private String apiUrl;
   
}
