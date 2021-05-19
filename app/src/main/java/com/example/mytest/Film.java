package com.example.mytest;

public class Film {
    private String name , description, imageUML;

    public Film(){}
    public Film(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getImageUML() {
        return imageUML;
    }

    public void setImageUML(String imageUML) {
        this.imageUML = imageUML;
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
}
