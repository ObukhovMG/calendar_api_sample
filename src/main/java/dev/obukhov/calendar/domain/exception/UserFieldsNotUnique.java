package dev.obukhov.calendar.domain.exception;

public class UserFieldsNotUnique extends RuntimeException {
    public UserFieldsNotUnique(String... strings) {
        super("Fields not unique:" + String.join(" ", strings));
    }

    public UserFieldsNotUnique() {
        super("Fields not unique: login or email");
    }
}
