package com.Shopping.Backend.User.User.API.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequesterUtil {
    public HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public HttpEntity createHttpEntity(HttpHeaders headers){
        return new HttpEntity(headers);
    }

    public HttpEntity createHttpEntity(Object body, HttpHeaders headers){
        return new HttpEntity(body, headers);
    }
}
