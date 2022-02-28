package dev.obukhov.calendar.persistence;

import dev.obukhov.calendar.domain.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CustomNativeRepository {
    List<Event> getAllDateTimeRange(UUID[] userIds, LocalDateTime startDateTime);
}
