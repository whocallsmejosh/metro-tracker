package com.azalea.metro.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetroResponse {

    @JsonProperty("Trains")
    private ArrayList<Train> trains = new ArrayList<>();

    public MetroResponse() {

    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains){
        this.trains = trains;
    }

    @Override
    public String toString() {
        return "MetroResponse{" +
                "trains=" + trains.toString() +
                '}';
    }
}
