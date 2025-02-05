package com.example.bootcamp.dto.entity.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDTO {
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private String aboutMe;
    private String telephone;
    private String email;

    private LocalDate birthday;
    private String city;
    private String telegramLink;
    private String vkLink;

    private String role;  // строка либо user, либо admin
    private Long center = null;  // id центра, в котором зарегистрирован

    private String centerName = null;
    private String centerImageUrl = null;

    private String profileImageUrl;
    private boolean medicalBook;
    private boolean driverLicense;
}
