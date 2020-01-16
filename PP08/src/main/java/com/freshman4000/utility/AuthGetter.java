package com.freshman4000.utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class AuthGetter {

    public static HttpEntity<String> getEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = Base64.getEncoder().encodeToString("admin:1234".getBytes());
        headers.set("Authorization", "Basic " + token);

        return new HttpEntity<>(body,headers);
    }
}
