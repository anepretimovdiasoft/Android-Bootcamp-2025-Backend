package com.example.edu.dto.person;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class PersonUpdateDTO {
    private @Nullable String name;
    private @Nullable String username;
    private @Nullable String email;
    private @Nullable String photoUrl;
    private @Nullable String info;
    private @Nullable String phone;
    private @Nullable String departmentName;
}
