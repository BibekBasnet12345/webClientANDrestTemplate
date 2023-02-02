package com.contact.controller;

import com.contact.Entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@RestController
public class UserWebClient {

  @Autowired
  private WebClient.Builder webClientBuilder;

  private static final String AUTHENTICATION_URL ="http://localhost:8080/user/authenticate";
  private static final String HELLO_URL = "http://localhost:8080/user/getUserById/3";

  @RequestMapping(value = "/getWebClientResponse", method = RequestMethod.GET)
  public String getResponse(@RequestBody User user)  {


      System.out.println("mhvbj,n.mm");
      String tok = webClientBuilder.build()
              .post()
              .uri(AUTHENTICATION_URL)
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
              .body(BodyInserters.fromValue(user))
              .retrieve()
              .bodyToMono(String.class)
              .block();

      System.out.println("hellloo");
      System.out.println(tok);

     String userName = Objects.requireNonNull(webClientBuilder.build()
              .get()
              .uri(HELLO_URL)
              .headers(headers -> headers.setBearerAuth(tok))
              .retrieve()
              .bodyToMono(User.class)
              .block()).getName();


      System.out.println(userName);


    return  userName;
  }

}
