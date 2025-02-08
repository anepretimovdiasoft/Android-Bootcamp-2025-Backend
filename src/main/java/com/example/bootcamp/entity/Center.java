package com.example.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "centers")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "center_volunteers", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "volunteer_ids")
    private List<Integer> volunteer_ids;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "center_tags", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "tags")
    private List<String> tags;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "center_active", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "active")
    private List<Integer> active;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "center")
    @JsonIgnore
    private List<Profile> profiles;
}