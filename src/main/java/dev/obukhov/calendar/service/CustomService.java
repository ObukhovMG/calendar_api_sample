package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.DateTimeRange;

import java.util.List;
import java.util.UUID;

public interface CustomService {
    DateTimeRange getNearestFreeSlot(List<UUID> userIds, Integer minDurationMinutes);
}
