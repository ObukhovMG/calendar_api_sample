package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.Event;
import dev.obukhov.calendar.domain.exception.NotFoundException;
import dev.obukhov.calendar.domain.exception.NotUniqueFields;
import dev.obukhov.calendar.domain.helper.ObjectProperties;
import dev.obukhov.calendar.persistence.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event get(String id) {
        return get(UUID.fromString(id));
    }

    @Override
    public Event get(UUID id) throws NotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<Event> getAll(UUID id, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        return eventRepository.getAll(id, dateTimeFrom, dateTimeTo);
    }

    @Override
    public void delete(UUID id) throws NotFoundException {
        eventRepository.delete(eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public Event create(Event event) throws NotUniqueFields {
        event.setId(null);
        try {
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueFields();
        }
    }

    @Override
    public Event update(Event event) throws NotFoundException, NotUniqueFields {
        get(event.getId());
        try {
            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueFields();
        }
    }

    @Override
    public Event patch(Event event) throws NotFoundException, NotUniqueFields {
        Event target = get(event.getId());
        BeanUtils.copyProperties(event, target, ObjectProperties.getNullFieldNames(event));
        try {
            return eventRepository.save(target);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueFields();
        }
    }


}
