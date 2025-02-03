package com.example.bootcamp.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ImageCenters")
public class ImageCenters {
    @Id
//    @ManyToOne
//    @JoinColumn(name = "Id", nullable = false)
    private long id;

    @Column(name = "LinkImage")
    private String linkImage;
}
