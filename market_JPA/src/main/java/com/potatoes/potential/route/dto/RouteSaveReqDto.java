package com.potatoes.potential.route.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteSaveReqDto {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @JsonIgnore
    private String totalTime;

    private RouteInfoSaveDto routeInfo;

    public void calTotalTime() {
        long totalSeconds = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
        setTotalTime(String.format("%02d:%02d:%02d", TimeUnit.SECONDS.toHours(totalSeconds),
                TimeUnit.SECONDS.toMinutes(totalSeconds) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.SECONDS.toSeconds(totalSeconds) % TimeUnit.MINUTES.toSeconds(1)));
    }

    private void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

}
