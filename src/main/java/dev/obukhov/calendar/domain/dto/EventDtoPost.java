package dev.obukhov.calendar.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EventDtoPost {
    private UUID ownerId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String repeatType;
    private List<UUID> attendees;
    private String privacyType;
    private String header;
    private String description;
    private Integer notifyMinutes;
}
