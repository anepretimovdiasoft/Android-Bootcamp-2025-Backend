package com.example.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

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
    @JsonIgnore
    @JoinColumn(name = "new_profile_id", nullable = false)
    private Profile newProfile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "old_profile_id", nullable = false)
    private Profile oldProfile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}