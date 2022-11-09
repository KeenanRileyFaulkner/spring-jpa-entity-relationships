package com.keena.dbrelationships.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String category;

//    @OneToOne
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Collection<Category> children;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
    private Collection<Book> books;

    public Collection<Category> getChildren() {
        if(children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public Collection<Book> getBooks() {
        if(books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    public void addChildCategory(Category childCategory) {
        this.getChildren().add(childCategory);
        childCategory.setParent(this);
    }

    public void addBook(Book book) {
        this.getBooks().add(book);
        book.getCategories().add(this);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category{id=")
                .append(id)
                .append(", category='")
                .append(category).append("'");
        if(parent != null) {
            stringBuilder.append(", parent.Category=")
                    .append(parent.getCategory());
        }
        if(children != null) {
            stringBuilder.append(", children=")
                    .append(children);
        }
        if(books != null) {
            stringBuilder.append(", books=")
                    .append(books);
        }
        return stringBuilder.toString();

//        return "Category{" +
//                "id=" + id +
//                ", category='" + category + '\'' +
//                ", parent=" + parent +
//                ", children=" + children +
//                ", books=" + books +
//                '}';
    }
}
