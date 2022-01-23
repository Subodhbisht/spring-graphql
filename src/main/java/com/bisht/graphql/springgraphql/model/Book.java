package com.bisht.graphql.springgraphql.model;

import javax.persistence.*;

@Table
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer isn;
    private String title;
    private String publisher;
    private String genre;
    private String[] authors;
    private String publishedDate;

    public Book() {
    }

    public Book(String title, String publisher, String genre, String[] authors, String publishedDate) {
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

    public Integer getIsn() {
        return isn;
    }

    public void setIsn(Integer isn) {
        this.isn = isn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
