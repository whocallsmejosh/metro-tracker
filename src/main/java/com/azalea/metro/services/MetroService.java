package com.azalea.metro.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MetroService {

    Logger logger = LogManager.getLogger(MetroService.class);

    //TODO: encrypt in properties
    @Value("${metro.api.key}")
    private String key;

//    @Value("${metro.api.jstationinfo.uri}")
//    private String jStationInfoUri;

    @Value("${metro.api.railprediction.uri}")
    private String railPredictionUri;

    public String getRailPredictionInfo (){
        logger.debug("start getRailPredictionInfo");
        ResponseEntity<String> responseEntity;
        String response;

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setCacheControl("no-cache");
            header.set("api_key",key);

            HttpEntity<String> requestEntity = new HttpEntity<>(header);
            responseEntity = restTemplate.exchange(railPredictionUri, HttpMethod.GET, requestEntity, String.class);

            response = responseEntity.getBody();

            logger.debug("end getRailPredictionInfo");

        } catch (Exception e) {
            logger.error("An error occurred calling the WMATA StationPrediction API ", e);
            return "";
        }

        return response;
    }
}
