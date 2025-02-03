package com.example.bootcamp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "center")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Название не может быть пустым!")
    @Size(max = 100, message = "Максимальная длина имени 100 символов!")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Описание не может быть пустым!")
    @Size(max = 300, message = "Максимальная длина описания 300 символов!")
    private String description;

    @Column(name = "address")
    @NotBlank(message = "Адрес не может быть пустым!")
    @Size(max = 200, message = "Максимальный размер адреса 200 символов!")
    private String address;

    @Column(name = "link_logo")
    @NotBlank(message = "Путь к логотипу не может быть пустой!")
    @Size(max = 200, message = "Максимальный размер пути к логотипу 200 символов!")
    private String linkLogo;

    @Column(name = "latitude")
    @NotNull(message = "Широта не может быть пустой!")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "Долгота не может быть пустой!")
    private Double longitude;

    @Column(name = "vk_link")
    @NotBlank(message = "VK ID не может быть пустым!")
    @Size(max = 255, message = "Максимальный размер VK ID 255 символов!")
    private String vkLink;

    @Column(name = "link_site")
    @NotBlank(message = "Ссылка на сайт не может быть пустой!")
    @Size(max = 255, message = "Максимальный размер ссылки на сайт 255 символов!")
    private String linkSite;

    @OneToMany(mappedBy = "center")
    private List<Volunteer> volunteers;

    @OneToMany(mappedBy = "center")
    private List<Event> events;
}
