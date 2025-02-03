package com.example.bootcamp.modal;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    private List<ProfileUpdateRequest> profileUpdateRequests;
}