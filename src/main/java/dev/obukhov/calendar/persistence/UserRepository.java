package dev.obukhov.calendar.persistence;

import dev.obukhov.calendar.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    boolean existsByLoginOrEmail(String login, String email);

    /*
     * для заданного списка пользователей и минимальной продолжительности встречи,
     *  найти ближайшей интервал времени, в котором все эти пользователи свободны.
     *
     *  endpoint GET /closest-interval
     *  meeting-time=60&users[]=1&users[]=2&
     *  return DateTimeRange
     *
     *  conditions:
     *  найти все встречи, где
     *  ownerId in userRange
     *  and
     *
     *  event user start
     *
     *  user 1 [start end, start end, start end]
     *  user 2 [start end, start end]
     *  user 3 [start end]
     *
     *
     *
     * select start
     *
     * 1) Select * from event where owner_is in [users] and (start_date_time >= current_date_time or current_date_time <= end_date_time)
     *  event1 user1 from to
     *  event2 user2 from to
     *  event3 user1 from to
     *
     *  attendees [id1, id2, id3]
     */

}
