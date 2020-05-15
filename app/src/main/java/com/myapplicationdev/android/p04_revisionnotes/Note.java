package com.myapplicationdev.android.p04_revisionnotes;

public class Note {
    private int id;
    private int stars;
    private String note;

    public Note(int id, int stars, String note) {
        this.id = id;
        this.stars = stars;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
