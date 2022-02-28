package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.DateTimeRange;
import dev.obukhov.calendar.persistence.CustomNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    CustomNativeRepository repository;

    @Override
    public DateTimeRange getNearestFreeSlot(List<UUID> userIds, Integer minDurationMinutes) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes((long) minDurationMinutes);
        List<UUID[]> listWrapper = new LinkedList<>();
        List<DateTimeRange> rangeList = repository.getAllDateTimeRange(userIds.toArray(UUID[]::new), start).stream()
                .map(event -> new DateTimeRange(event.getStartDateTime(), event.getEndDateTime()))
                .collect(Collectors.toList());

        if (rangeList == null || rangeList.isEmpty())
            return new DateTimeRange(start, null);
        if (end.compareTo(rangeList.get(0).getFrom()) <= 0)
            return new DateTimeRange(start, rangeList.get(0).getFrom());

        start = rangeList.get(0).getTo();
        end = start.plusMinutes((long) minDurationMinutes);

        for (int i = 0; i < rangeList.size() - 1; i++) {
            DateTimeRange current = rangeList.get(i);
            DateTimeRange next = rangeList.get(i + 1);
            if (start.compareTo(current.getTo()) >= 0 && end.compareTo(next.getFrom()) <= 0)
                return new DateTimeRange(start, next.getFrom());
            if (start.compareTo(current.getTo()) < 0) {
                start = current.getTo();
                end = start.plusMinutes((long) minDurationMinutes);
            }
        }

        return new DateTimeRange(
                rangeList.get(rangeList.size() - 1).getTo(),
                null
        );
        //get list of ranges
        //iter through list
        /*
            min 2
            1 -  2
                1.5 - 3
                         4 - 5
                                  7 - 8
         1 - 2 3 - 4 5 - 6
         start = current
         finish = start + min

         1) time before current
             if (finish <= list[0].start) return (start, finish)
         1.1)
            return (start, finish)

         for (range : List<Range>) {
            current = list[0]
            next = list[+1]

            2) time between two
             if {
                start >= current.end && finish <= next.start
                return
             } else {
                start = current.end;
                finish = start + min;
             }
         }
         3) last time
         return (
             start = return list[list.size()-1].end,
             finish = start + min
         )

         */
    }
}
