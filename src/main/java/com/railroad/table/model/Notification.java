package com.railroad.table.model;

import com.railroad.table.dto.StationDto;
import com.railroad.table.dto.TrainInfoDto;

import java.util.List;

public class Notification {

    private String method;
    private List<StationDto> notificationStationDto;
    private List<TrainInfoDto> notificationTrains;

    public List<StationDto> getNotificationStationDto() {
        return notificationStationDto;
    }

    public void setNotificationStationDto(List<StationDto> notificationStationDto) {
        this.notificationStationDto = notificationStationDto;
    }

    public List<TrainInfoDto> getNotificationTrains() {
        return notificationTrains;
    }

    public void setNotificationTrains(List<TrainInfoDto> notificationTrains) {
        this.notificationTrains = notificationTrains;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
