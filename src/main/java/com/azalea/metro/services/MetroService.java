package com.azalea.metro.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MetroService {

    //TODO: encrypt in properties
    @Value("${metro.api.key}")
    private String key;

    //TODO: change to upcoming rail
    @Value("${metro.api.jstations.uri}")
    private String jStations_uri;

    public ResponseEntity<Object> getLineStations (){
        ResponseEntity<String> responseEntity = null;
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setCacheControl("no-cache");
            header.set("api_key",key);

            HttpEntity<String> requestEntity = new HttpEntity<String>(header);
            responseEntity = restTemplate.exchange(jStations_uri, HttpMethod.GET, requestEntity, String.class);

            //TODO: fix response format, domain objects?
            String response = responseEntity.getBody();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error! Something went wrong...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
