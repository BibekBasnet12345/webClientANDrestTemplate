package com.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ContactServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactServiceApplication.class, args);
        System.out.println("working=--------------");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }
    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return  WebClient.builder();

    }

}
