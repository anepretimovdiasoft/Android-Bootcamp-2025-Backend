package com.example.bootcamp.util;

import java.util.Set;

public class MessageMapper {

    private static final Set<String> VALID_STATUSES = Set.of("sent", "failed", "canceled", "read");

    public static boolean isValidStatus(String status) {
        return VALID_STATUSES.contains(status);
    }

}
