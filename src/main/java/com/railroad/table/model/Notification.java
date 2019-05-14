package com.railroad.table.model;

import com.railroad.table.dto.StationDto;
import com.railroad.table.dto.TrainInfoDto;
import lombok.Data;
import java.util.List;

@Data
public class Notification {

    private String method;
    private List<StationDto> notificationStationDto;
    private List<TrainInfoDto> notificationTrains;

}
