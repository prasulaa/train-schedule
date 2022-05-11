package com.example.viewdelayapp;

public class Train {

    private Long id;
    private String name;

    public Train() {
        super();
    }

    public Long getId() {
        return id;
    }

    public Train(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
