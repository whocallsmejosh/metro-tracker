package com.azalea.metro.controllers;

import com.azalea.metro.services.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MetroController {

    @Autowired
    MetroService metroService;

    //TODO: add parameter for metro line
    @RequestMapping(value="/metro", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMetroData() {

        try {

            ResponseEntity<Object> response = metroService.getLineStations();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error! Something went wrong...", HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
}
