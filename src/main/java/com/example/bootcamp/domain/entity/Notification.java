package com.example.bootcamp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_whom_id", referencedColumnName = "id", nullable = false)
    private Volunteer toWhom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_whom_id", referencedColumnName = "id", nullable = false)
    private Volunteer fromWhom;

    @Column(name = "message")
    @NotBlank(message = "Сообщение не может быть пустым!")
    @Size(max = 500, message = "Максимальная длина сообщения 500 символов!")
    private String message;

    @CreatedDate
    @Column(name = "date_dispatch", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDate dateDispatch;
}
