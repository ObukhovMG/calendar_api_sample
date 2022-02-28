package dev.obukhov.calendar.web.controller;

import dev.obukhov.calendar.domain.dto.UserDtoResp;
import dev.obukhov.calendar.domain.entity.User;
import dev.obukhov.calendar.domain.maper.UserMapper;
import dev.obukhov.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDtoResp getUser(@PathVariable("id") UUID id) {
        return UserMapper.INSTANCE.userToUserDtoResp(userService.get(id));
    }

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UUID id) {
       userService.delete(id);
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public UserDtoResp createUser(@RequestBody User user) {
        return UserMapper.INSTANCE.userToUserDtoResp(userService.create(user));
    }

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json")
    public UserDtoResp updateUser(@RequestBody User user, @PathVariable UUID id) {
        user.setId(id);
        return UserMapper.INSTANCE.userToUserDtoResp(userService.update(user));
    }

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.PATCH,
            consumes = "application/json",
            produces = "application/json")
    public UserDtoResp patchUser(@RequestBody User user, @PathVariable UUID id) {
        user.setId(id);
        return UserMapper.INSTANCE.userToUserDtoResp(userService.patch(user));
    }
}
