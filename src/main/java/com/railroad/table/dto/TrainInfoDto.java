package com.railroad.table.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class TrainInfoDto implements Serializable {

    private int number;

    private String station;

    private String departStation;

    private String arrivalStation;

    private String arrivalDate;

    private String departureDate;

    public TrainInfoDto() {
    }

    public TrainInfoDto(String station, int number, String departStation, String arrivalStation, String arrivalDate, String departureDate) {
        this.station = station;
        this.number = number;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.departStation = departStation;
        this.arrivalStation =arrivalStation;
    }


}
