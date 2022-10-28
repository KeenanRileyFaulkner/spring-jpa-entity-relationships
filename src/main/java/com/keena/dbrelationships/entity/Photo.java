package com.keena.dbrelationships.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "photo")
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(nullable = false)
    private String urlSmall;

    @Column(nullable = false)
    private String urlMedium;

    @Column(nullable = false)
    private Date urlLarge;

    @OneToOne(mappedBy = "photo")
    private Book book;
}
