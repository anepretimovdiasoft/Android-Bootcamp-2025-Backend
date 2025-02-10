package com.example.edu.utils;

import com.example.edu.utils.mappers.EntityMapper;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@UtilityClass
public class GlobalUtils {
    public boolean hasAnyNullField(Object obj, @Nullable List<String> fieldsToIgnore) {
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> fieldsToIgnore == null || !fieldsToIgnore.contains(field.getName()))
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

    public <EntityT, DtoT> List<DtoT> convertAllToDTO(Collection<EntityT> entities, EntityMapper<EntityT, DtoT> mapper) {
        return entities.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
