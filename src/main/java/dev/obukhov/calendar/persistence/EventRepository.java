package dev.obukhov.calendar.persistence;

import dev.obukhov.calendar.domain.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends CrudRepository<Event, UUID> {
    /*

        a   b
        5 - 6
        7 - 8


        x1  x2
        3 - 7

        x1 <= a && a <= x2
        ||
        x1 <= b && b <= x2
        StartDateTimeBetweenOrEndDateTimeBetween

        @Query(value = "from EntityClassTable t where yourDate BETWEEN :startDate AND :endDate")
public List<EntityClassTable> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
     */



    @Query(value = "from Event e where " +
            "e.owner.id = :id and " +
            "((e.startDateTime BETWEEN :dtFrom AND :dtTo) or " +
            "(e.endDateTime BETWEEN :dtFrom AND :dtTo))"
    )
    List<Event> getAll(@Param("id") UUID id,
                              @Param("dtFrom") LocalDateTime dateTimeFrom,
                              @Param("dtTo") LocalDateTime dateTimeTo);

    @Query(nativeQuery = true, value = """
        select distinct on (e.event_id) *
            from calendar_api.event e   
            where (e.start_date_time >= :start_time
                    or e.end_date_time > :start_time)
                and (e.owner_id in :user_ids
                 or e.attendees && regexp_replace(:user_ids,'[()]', '', 'g'))
    """)
    List<Event> getAllDateTimeRange(@Param("user_ids") UUID[] userIds,
                                    @Param("start_time") LocalDateTime startDateTime
    );






}
