package com.luv2code.books.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "category")
    private String category;

    @ElementCollection
    @CollectionTable(
            name = "book_rating",
            joinColumns = @JoinColumn(name = "book_id")
    )
    @Column(name = "rating")
    private List<Integer> ratings;
    @CreationTimestamp
    @Column(updatable = false,name="created_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "owner_id" ,nullable = false)
    private User owner;

    public Book(String title, String author, String category, List<Integer> ratings, User owner) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.ratings = ratings;
        this.owner = owner;
    }

    public Book() {
    }

    public Book(String title, String author, String category, User owner) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.owner = owner;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addRating(int rating) {
        if (this.ratings == null) {
            this.ratings = new ArrayList<>();
        }
        ratings.add(rating);
    }

    public Double getRatingAvg(){
        return  this.getRatings().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}
