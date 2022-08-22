package cn.anntek.springdatajparestbookapp.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String publisher;

    @Column(nullable = true)
    private String publishedDate;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = true)
    private String photo;
    public Book(){

    }

    public Book(String isbn, String name, String publisher, String publishedDate, String photo) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
