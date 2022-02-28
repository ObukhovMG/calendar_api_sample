package dev.obukhov.calendar.web.util;

import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

//@Component
public class StringToUUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {
        return UUID.fromString(source);
    }
}
