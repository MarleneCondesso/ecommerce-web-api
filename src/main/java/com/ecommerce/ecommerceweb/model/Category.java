package com.ecommerce.ecommerceweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "name")
    private @NotBlank String name;

    private @NotBlank String description;

    private @NotBlank String image;

    public Category(){

    }

    public Category(@NotBlank String name, @NotBlank String description){
        this.name = name;
        this.description = name;
    }

    public Category(@NotBlank String name, @NotBlank String description, @NotBlank String image){
        this.name = name;
        this.description = name;
        this.image = image;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
