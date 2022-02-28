package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {
    Event get(String id);
    Event get(UUID id);
    List<Event> getAll(UUID id, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);
    void delete(UUID id);
    Event create(Event event);
    Event update(Event event);
    Event patch(Event event);
}
