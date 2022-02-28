package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.User;

import java.util.UUID;

public interface UserService {
    User get(String id);
    User get(UUID id);
    void delete(UUID id);
    User create(User user);
    User update(User user);
    User patch(User user);
}
