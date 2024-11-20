package com.azalea.metro.controllers;

import com.azalea.metro.domains.MetroResponse;
import com.azalea.metro.services.MetroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MetroController {

    Logger logger = LogManager.getLogger(MetroController.class);

    @Autowired
    MetroService metroService;

    //TODO: add parameter for metro line
    @RequestMapping(value="/metro", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMetroData() {
        logger.debug("Start getMetroData");
        try {

            String response = metroService.getRailPredictionInfo();

            ObjectMapper mapper = new ObjectMapper();

            MetroResponse jsonResponse = new MetroResponse();

            try {
                jsonResponse = mapper.readValue(response, MetroResponse.class);
            } catch (JsonProcessingException e) {
                logger.error("error parsing json ", e);
            }

            logger.debug("End getMetroData");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("An error occurred calling the metroService ", e);
            return new ResponseEntity<>("Error! Something went wrong...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
