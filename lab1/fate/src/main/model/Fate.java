package main.model;

public class Fate {

    private int id;
    private String description;

    public Fate(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}