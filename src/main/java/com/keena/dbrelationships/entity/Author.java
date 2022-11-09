package com.keena.dbrelationships.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//@Data
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    /*
     * when defining table column name, the convention is to use underscore "_"
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Collection<Book> books;

    public Collection<Book> getBooks() {
        if(books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    public void addBooks(Book book) {
        this.getBooks().add(book);
        book.getAuthors().add(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Author{id=")
                .append(id)
                .append(", firstName='")
                .append(firstName).append("'")
                .append(", lastName='")
                .append(lastName).append("'")
                .append(", birthDate='")
                .append(birthDate).append("'");
        if(books != null) {
            stringBuilder.append(", books.size=")
                    .append(getBooks().size());
        }
        return stringBuilder.toString();

//        return "Author{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", birthDate=" + birthDate +
////                ", books=" + books +
//                '}';
    }
}
