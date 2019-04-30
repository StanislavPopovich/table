package com.railroad.table.dto;

import java.io.Serializable;
import java.util.Objects;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDepartStation() {
        return departStation;
    }

    public void setDepartStation(String departStation) {
        this.departStation = departStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainInfoDto that = (TrainInfoDto) o;
        return number == that.number &&
                Objects.equals(station, that.station) &&
                Objects.equals(departStation, that.departStation) &&
                Objects.equals(arrivalStation, that.arrivalStation) &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, station, departStation, arrivalStation, arrivalDate, departureDate);
    }

    @Override
    public String toString() {
        return "TrainInfoDto{" +
                "number=" + number +
                ", station='" + station + '\'' +
                ", departStation='" + departStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
