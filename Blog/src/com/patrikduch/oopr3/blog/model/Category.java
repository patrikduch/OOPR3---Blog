package com.patrikduch.oopr3.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Category")
public class Category {

    public Category() {

    }

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;
    @Column(name = "categoryName")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "visible")
    private boolean visible;

    public Category(String name, String description, boolean visible) {
        this.name = name;
        this.description = description;
        this.visible = visible;
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

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }



}
