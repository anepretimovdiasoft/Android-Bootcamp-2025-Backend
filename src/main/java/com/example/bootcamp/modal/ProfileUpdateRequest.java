package com.example.bootcamp.modal;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile_update_requests")
public class ProfileUpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "new_profile_id", nullable = false)
    private Profile newProfile;

    @ManyToOne
    @JoinColumn(name = "old_profile_id", nullable = false)
    private Profile oldProfile;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}