package com.keena.dbrelationships.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private Long isbn;

    /*
     * when defining table column name, the convention is to use underscore "_"
     */
    @Column(name = "total_pages")
    private Integer totalPages;

    private Double rating;

    @Column(name = "published_date")
    private Date publishedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="photo_id")
    private Photo photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Category> categories;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST)
    private Collection<Author> authors;

    public Collection<Category> getCategories() {
        if(categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public Collection<Author> getAuthors() {
        if(authors == null) {
            authors = new ArrayList<>();
        }
        return authors;
    }

    public void addPhoto(Photo photo) {
        this.photo = photo;
        photo.setBook(this);
    }

    public void addAuthor(Author author) {
        this.getAuthors().add(author);
        author.getBooks().add(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                ", totalPages=" + totalPages +
                ", rating=" + rating +
                ", publishedDate=" + publishedDate +
                ", photo=" + photo +
                ", categories.size=" + getCategories().size() +
                ", authors=" + getAuthors() +
                '}';
    }
}
