package dev.obukhov.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResp {
    private UUID id;
    private String name;
    private String login;
    private String email;
    private ZoneId timezone;
    private LocalTime workDayStartTime;
    private LocalTime workDayEndTime;
}
