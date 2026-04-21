package com.luv2code.books.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookDto {

    long id;

    @Size(min = 1, max = 30 , message = "Title is between 1 and 30 ")
    @NotBlank(message = "Title Must Have a Name")
    private String title;

    @Size(min = 1, max = 40 , message = "Author is between 1 and 40 ")
    @NotBlank(message = "Must Have Author")
    private String author;

    @Size(min = 1, max = 30 , message = "Category is between 1 and 30 ")
    private String category;

    @Min(value = 1 , message = "Rating must be at least 1")
    @Max(value = 5 , message = "Rating cannot go past 5  ")
    private int rating;

    public BookDto() {
    }

    public BookDto(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }

    public BookDto(long id, String title, String author, String category, int rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
