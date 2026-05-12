package com.luv2code.books.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class BookResponse {

    private String title;

    private String author;

    private String category;

    private Double rating;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime publishedAt;

    public BookResponse(String title, String author, String category, Double rating, LocalDateTime publishedAt) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
