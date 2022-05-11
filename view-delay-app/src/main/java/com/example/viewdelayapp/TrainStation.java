package com.example.viewdelayapp;

public class TrainStation {

    public TrainStation() {
        super();
    }

    private Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
