package dev.obukhov.calendar.web.controller;

import dev.obukhov.calendar.domain.dto.EventDtoPost;
import dev.obukhov.calendar.domain.dto.EventDtoResp;
import dev.obukhov.calendar.domain.entity.Event;
import dev.obukhov.calendar.domain.maper.EventMapper;
import dev.obukhov.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class EventController {
    @Autowired
    EventService eventService;

    @RequestMapping(
            value = "/events/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public EventDtoResp getEvent(@PathVariable("id") UUID id) {
        return EventMapper.INSTANCE.eventToEventDtoResp(eventService.get(id));
    }

    @RequestMapping(
            value = "/events/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("id") UUID id) {
        eventService.delete(id);
    }

    @RequestMapping(
            value = "/events/{id}",
            method = RequestMethod.PATCH,
            consumes = "application/merge-patch+json")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public EventDtoResp updateEvent(
            @PathVariable("id") UUID id,
            @RequestBody Event event) {
        event.setId(id);
        return EventMapper.INSTANCE.eventToEventDtoResp(eventService.patch(event));
    }

    @RequestMapping(
            value = "/events",
            method = RequestMethod.POST,
            consumes = "application/json")
    public EventDtoResp createEvent(@RequestBody EventDtoPost eventDtoPost) {
        Event event = EventMapper.INSTANCE.eventDtoPostToEvent(eventDtoPost);
        return EventMapper.INSTANCE.eventToEventDtoResp(eventService.create(event));
    }

    //* найти все встречи пользователя для заданного промежутка времени;
    @RequestMapping(
            value = "/users/{id}/events",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public List<EventDtoResp> getEventsByUserAndTimePeriod(
            @PathVariable("id") UUID id,
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeFrom,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeTo) {
        List<Event> events = eventService.getAll(id, dateTimeFrom, dateTimeTo);

        return EventMapper.INSTANCE.eventsToEventDtoResps(events);
    }

    @RequestMapping(
            value = "/events/{id}/invite",
            method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void inviteAction(
            @PathVariable("id") String id,
            @RequestParam String action) {
        //todo
    }
}
