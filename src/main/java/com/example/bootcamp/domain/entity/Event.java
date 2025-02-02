package com.example.bootcamp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Название не может быть пустым!")
    @Size(max = 100, message = "Максимальная длина имени 100 символов!")
    private String name;

    @Column(name = "description")
    @Size(max = 300, message = "Максимальная длина описания 300 символов!")
    private String description;

    @Column(name = "image_link")
    @NotBlank(message = "Ссылка на изображение не может быть пустой!")
    @Size(max = 100, message = "Максимальная длина ссылки 100 символов!")
    private String imageLink;

    @Column(name = "address")
    @NotBlank(message = "Адрес не может быть пустой!")
    @Size(max = 300, message = "Максимальная длина адреса 100 символов!")
    private String address;

    @Column(name = "latitude")
    @NotNull(message = "Широта не может быть пустой!")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "Долгота не может быть пустой!")
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;
}
