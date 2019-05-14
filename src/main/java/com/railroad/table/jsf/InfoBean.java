package com.railroad.table.jsf;
import com.railroad.table.dto.StationDto;
import com.railroad.table.dto.TrainInfoDto;
import com.railroad.table.ejb.TableService;
import com.railroad.table.model.Notification;
import lombok.Data;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class InfoBean {
    private final static Logger logger = Logger.getLogger(InfoBean.class);


    private String station = "";
    private List<TrainInfoDto> schedule;
    private List<StationDto> stationDtos = new ArrayList<>();
    private List<TrainInfoDto> trains = new ArrayList<>();

    @Inject
    private TableService tableService;

    @Inject @Push
    private PushContext push;


    @PostConstruct
    public void init() {
        this.stationDtos = tableService.initialStationsDataLoad();
        this.trains = tableService.initialScheduleDataLoad();
    }

    public String moveToPage(){
        setScheduleForSelectedStation();
        return "schedulePage";
    }


    public void onNewNotification(@Observes Notification notification) {
        if(notification.getMethod().equals("updateStation")){
            logger.info("stations have updated");
            this.stationDtos = notification.getNotificationStationDto();
        }
        if(notification.getMethod().equals("updateSchedule")){
            logger.info("schedule have updated ");
            this.trains = notification.getNotificationTrains();
            setScheduleForSelectedStation();
            push.send("updateNotifications");
        }
    }

    private void setScheduleForSelectedStation(){
        this.schedule = new ArrayList<>();
        List<TrainInfoDto> allTrains = getTrains();
        for(TrainInfoDto trainInfoDto: allTrains){
            if(trainInfoDto.getStation().equals(station)){
                this.schedule.add(trainInfoDto);
            }
        }
    }
}
