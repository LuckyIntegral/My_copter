package my.copter.data.dto.statistic;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;

import java.util.Date;

@Getter
@Setter
public class EventHistoryDto {
    private Long eventId;
    private Date date;

    public EventHistoryDto(DroneStatisticIndex index) {
        this.date = index.getCreated();
        this.eventId = index.getDroneId();
    }
}
