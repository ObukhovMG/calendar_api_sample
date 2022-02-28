package dev.obukhov.calendar.domain.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Could not find entity " + id.toString());
    }
}
