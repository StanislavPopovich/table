package com.railroad.table.ejb;

import com.railroad.table.dto.StationDto;
import com.railroad.table.dto.TrainInfoDto;
import com.railroad.table.jms.Recv;
import com.railroad.table.model.Notification;
import org.apache.log4j.Logger;
import javax.ejb.Singleton;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TableService {

    @Inject
    private BeanManager beanManager;

    @Inject
    private Recv recv;

    public void updateStations(String method, byte[] data) {
        Notification notification = new Notification();
        notification.setNotificationStationDto(getStationsNames(new String(data)));
        notification.setMethod(method);
        beanManager.fireEvent(notification);
    }

    public void updateSchedule(String method, byte[] data) {
        Notification notification = new Notification();
        notification.setNotificationTrains(getSchedule(new String(data)));
        notification.setMethod(method);
        beanManager.fireEvent(notification);
    }

    public List<StationDto> initialStationsDataLoad() {
        String stations = new String(recv.receiveMessage("railroad.stations"));
        return getStationsNames(stations);
    }

    public List<TrainInfoDto> initialScheduleDataLoad() {
        String trains = new String(recv.receiveMessage("railroad.schedule"));
        return getSchedule(trains);
    }

    private List<StationDto> getStationsNames(String message){
        String[] data = message.split("/");
        String[] stations = data[1].split(",");
        List<StationDto> stationDtos = new ArrayList<>();
        for(int i = 0; i < stations.length; i++){
            StationDto stationDto = new StationDto();
            stationDto.setName(stations[i]);
            stationDtos.add(stationDto);
        }
        return stationDtos;
    }

    private  List<TrainInfoDto> getSchedule(String messege){
        String[] trains = messege.split("/");
        List<TrainInfoDto> trainInfoDtos = new ArrayList<>();
        for(int i = 1; i < trains.length; i++){
            String[] train = trains[i].split(",");
            TrainInfoDto trainInfoDto = new TrainInfoDto(train[0] ,new Integer(train[1]), train[2], train[3], train[4], train[5]);
            trainInfoDtos.add(trainInfoDto);
        }
        return trainInfoDtos;
    }
}
