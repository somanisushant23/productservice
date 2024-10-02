package com.sushant.productservice.commons;

import com.sushant.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthCommons {

    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String token) {
        ResponseEntity<UserDto> userDtoResponse = restTemplate.postForEntity(
                "http://localhost:8181/users/validate/"+token, null, UserDto.class
        );

        if(userDtoResponse.getBody() == null) return false;
        return true;
    }
}
