package com.shoppingbackend.Front.API.controller;

import com.shoppingbackend.Front.API.DTO.UserSingUPDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class PortalUserController {
    @PostMapping("/sign")
    public ResponseEntity<UserSingUPDTO> signup(@RequestBody UserSingUPDTO userSingUPDTO) {
        String url = "http://localhost:8080/db/user/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserSingUPDTO> entity = new HttpEntity<>(userSingUPDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.POST, entity, UserSingUPDTO.class);

    }

}