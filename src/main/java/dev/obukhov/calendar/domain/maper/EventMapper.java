package dev.obukhov.calendar.domain.maper;

import dev.obukhov.calendar.domain.dto.EventDtoPost;
import dev.obukhov.calendar.domain.dto.EventDtoResp;
import dev.obukhov.calendar.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "ownerId", target = "owner.id")
    Event eventDtoPostToEvent(EventDtoPost eventDtoPost);

    @Mapping(source = "owner.id", target = "ownerId")
    EventDtoResp eventToEventDtoResp(Event event);

    List<EventDtoResp> eventsToEventDtoResps(List<Event> events);
}
