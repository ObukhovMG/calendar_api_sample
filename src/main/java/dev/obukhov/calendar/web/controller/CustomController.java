package dev.obukhov.calendar.web.controller;

import dev.obukhov.calendar.domain.entity.DateTimeRange;
import dev.obukhov.calendar.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class CustomController {
    @Autowired
    CustomService customService;

    @RequestMapping(
            value = "/custom/nearest-free-slot",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public DateTimeRange getEvent(@RequestParam("minDurationMinutes") Integer minDurationMinutes,
                                  @RequestParam("userIdArr") List<UUID> userIds) {
        return customService.getNearestFreeSlot(userIds, minDurationMinutes);
    }
}
