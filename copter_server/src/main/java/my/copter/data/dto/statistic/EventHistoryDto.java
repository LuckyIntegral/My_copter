package my.copter.data.dto.statistic;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventHistoryDto {
    private Long eventId;
    private Date date;
}
