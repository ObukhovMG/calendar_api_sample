package dev.obukhov.calendar.domain.helper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ObjectProperties {
    public static String[] getNullFieldNames(Object obj) {
        List<String> list = new LinkedList<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    list.add(field.getName());
                }
            } catch (IllegalAccessException e) {
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
