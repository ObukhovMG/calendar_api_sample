package dev.obukhov.calendar.persistence;

import com.vladmihalcea.hibernate.type.array.UUIDArrayType;
import dev.obukhov.calendar.domain.entity.Event;
import org.hibernate.jpa.TypedParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomNativeRepositoryImpl implements CustomNativeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Event> getAllDateTimeRange(UUID[] userIds, LocalDateTime startDateTime) {
        return entityManager.createNativeQuery("""
            select  *
                        from calendar_api.event e   
                        where (e.start_date_time >= :start_time
                                or e.end_date_time > :start_time)
                            and (e.owner_id in :user_ids_uuid_record
                             or e.attendees && :user_ids_uuid_arr)
                        group by e.event_id
                        order by e.start_date_time, e.end_date_time
        """, Event.class
        ).setParameter("user_ids_uuid_arr", new TypedParameterValue(UUIDArrayType.INSTANCE, userIds))
                .setParameter("user_ids_uuid_record", Arrays.asList(userIds))
                .setParameter("start_time", startDateTime)
                .getResultList();
    }
}
