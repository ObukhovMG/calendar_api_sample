create table calendar_api."user"
(
    user_id             uuid default uuid_generate_v4() not null
        constraint user_pk
            primary key,
    name                varchar                         not null,
    login               varchar                         not null,
    password            varchar                         not null,
    email               varchar                         not null,
    timezone            varchar,
    work_day_start_time time,
    work_day_end_time   time
);

alter table calendar_api."user"
    owner to postgres;

create unique index user_email_uindex
    on calendar_api."user" (email);

create unique index user_login_uindex
    on calendar_api."user" (login);

grant delete, insert, references, select, trigger, truncate, update on calendar_api."user" to calendar_api;

create table calendar_api.event
(
    event_id        uuid default uuid_generate_v4() not null
        constraint event_pk
            primary key,
    owner_id        uuid                            not null
        constraint event_user_user_id_fk
            references calendar_api."user",
    start_date_time timestamp                       not null,
    end_date_time   timestamp,
    repeat_type     varchar,
    attendees       uuid[],
    privacy_type    varchar,
    header          varchar,
    description     text,
    status          varchar,
    notify_minutes  integer
);

alter table calendar_api.event
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on calendar_api.event to calendar_api;

