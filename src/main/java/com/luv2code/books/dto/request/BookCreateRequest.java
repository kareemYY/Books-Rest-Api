package com.luv2code.books.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookCreateRequest {


    @Size(min = 1, max = 30 , message = "Title is between 1 and 30 ")
    @NotBlank(message = "Title Must Have a Name")
    private String title;

    @Size(min = 1, max = 30 , message = "Category is between 1 and 30 ")
    private String category;


    public BookCreateRequest() {
    }

    public BookCreateRequest(String title, String category) {
        this.title = title;

        this.category = category;

    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
