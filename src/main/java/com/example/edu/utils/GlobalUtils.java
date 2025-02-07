package com.example.edu.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.function.Consumer;

@UtilityClass
public class GlobalUtils {
    public boolean hasAnyNullField(Object obj) {
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .anyMatch(field -> {
                    try {
                        return field.get(obj) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) setter.accept(value);
    }
}
