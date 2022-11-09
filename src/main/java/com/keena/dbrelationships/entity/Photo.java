package com.keena.dbrelationships.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

//@Data
@Entity
@Table(name = "photo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(nullable = false, name = "url_small")
    private String urlSmall;

    @Column(nullable = false, name = "url_medium")
    private String urlMedium;

    @Column(nullable = false, name = "url_large")
    private String urlLarge;

    @OneToOne(mappedBy = "photo")
    private Book book;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Photo{id=")
                .append(id)
                .append(", urlSmall='")
                .append(urlSmall).append("'")
                .append(", urlMedium='")
                .append(urlMedium).append("'")
                .append(", urlLarge='")
                .append(urlLarge).append("'");
        if(book != null) {
            stringBuilder.append(", parent.Title=")
                    .append(book.getTitle());
        }
         return stringBuilder.toString();

//        return "Photo{" +
//                "id=" + id +
//                ", urlSmall='" + urlSmall + '\'' +
//                ", urlMedium='" + urlMedium + '\'' +
//                ", urlLarge='" + urlLarge + '\'' +
//                ", book=" + book +
//                '}';
    }
}
