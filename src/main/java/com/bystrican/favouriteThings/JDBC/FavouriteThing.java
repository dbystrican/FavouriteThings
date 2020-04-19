package com.bystrican.favouriteThings.JDBC;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
@Entity
@Table(name="favourite_things",schema = "public")
@ManagedBean
@RequestScoped
public class FavouriteThing implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="rating")
    private int rating;
    @Transient
    private static Map<String,Integer> ratings;
    static{
        ratings = new LinkedHashMap<String,Integer>();
        ratings.put("Bad", 1); //label, value
        ratings.put("Not that bad", 2);
        ratings.put("Ok", 3);
        ratings.put("Pretty good", 4);
        ratings.put("Excelent", 5);

    }

    public Map<String,Integer> getRatings() {
        return ratings;
    }


    public FavouriteThing() {
    }

    public FavouriteThing(int id, String name, String description, int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public FavouriteThing(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "FavouriteThing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }

}
