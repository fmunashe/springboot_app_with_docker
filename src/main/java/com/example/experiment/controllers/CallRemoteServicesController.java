package com.example.experiment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@RestController
public class CallRemoteServicesController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/remoteAPI")
    public String getQuotes() {
        try {
            // request url
            String url = "http://196.2.66.6:8010/api/fetchToken";

            // create auth credentials
            String authStr = "Premier:premier@apiagribank";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            //headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class,"Premier","premier@apiagribank");

            // get JSON response
            String json = response.getBody();
            return response.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return "";
    }
}
