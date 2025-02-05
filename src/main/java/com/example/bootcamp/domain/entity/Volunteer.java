package com.example.bootcamp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "volunteer")
public class Volunteer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Имя пользователя не может быть пустым!")
    @Size(max = 100, message = "Максимальная длина имени 100 символов!")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Фамилия пользователя не может быть пустой!")
    @Size(max = 100, message = "Максимальная длина фамилии 100 символов!")
    private String surname;

    @Column(name = "patronymic")
    @Size(max = 100, message = "Максимальная длина отчества 100 символов!")
    private String patronymic;

    @Column(name = "about_me")
    @Size(max = 300, message = "Максимальная длина поля 'О себе' 300 символов!")
    private String aboutMe;

    @Column(name = "telephone", unique = true)
    @NotBlank(message = "Телефон не может быть пустым!")
    @Size(max = 20, message = "Максимальная длина телефонного номера 20 символов!")
    private String telephone;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email не может быть пустым!")
    @Size(max = 255, message = "Максимальная длина email 255 символов")
    @Email(message = "Email адрес должен быть в формате user@example.com!")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Пароль не может быть пустым!")
    @Size(max = 300, message = "Максимальная длина пароля 300 символов!")
    private String password;

    @Column(name = "birthday")
    @NotNull(message = "Дата рождения не может быть пустой!")
    private LocalDate birthday;

    @Column(name = "city")
    @NotBlank(message = "Название города не может быть пустым!")
    @Size(max = 50, message = "Максимальная длина названия города 50 символов!")
    private String city;

    @Column(name = "telegram_link")
    @Size(max = 300, message = "Максимальная длина telegram ID 300 символов!")
    private String telegramLink;

    @Column(name = "vk_link")
    @Size(max = 300, message = "Максимальная длина VK ID 300 символов!")
    private String vkLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center = null;

    @Column(name = "profile_image_url")
    @Size(max = 100, message = "Максимальная длина адреса изображения 100 символов!")
    private String profileImageUrl;

    @Column(name = "medical_book")
    private boolean medicalBook;

    @Column(name = "driver_license")
    private boolean driverLicense;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "toWhom", cascade = CascadeType.REMOVE)
    private List<Notification> notificationsToWhom;

    @OneToMany(mappedBy = "fromWhom", cascade = CascadeType.REMOVE)
    private List<Notification> notificationsFromWhom;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
