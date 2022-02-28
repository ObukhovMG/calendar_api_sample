package dev.obukhov.calendar.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table (name = "user", schema = "calendar_api")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(nullable = false, unique = true)
    private String login;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Convert(converter = Jsr310JpaConverters.ZoneIdConverter.class)
    private ZoneId timezone;

    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime workDayStartTime;

    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime workDayEndTime;
}
