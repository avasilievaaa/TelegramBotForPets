package com.course7.telegrambotforpets.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoPath;
    private String diet;
    private String mood;

    private String change;
    @ManyToOne
    @JoinColumn(name ="user")
    private User user;

    public Report(String photoPath, String diet, String mood, String change, User user) {
        this.photoPath = photoPath;
        this.diet = diet;
        this.mood = mood;
        this.change = change;
        this.user = user;
    }

    public Report() {

    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(photoPath, report.photoPath) && Objects.equals(diet, report.diet) && Objects.equals(mood, report.mood) && Objects.equals(change, report.change) && Objects.equals(user, report.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, diet, mood, change, user);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", photoPath='" + photoPath + '\'' +
                ", diet='" + diet + '\'' +
                ", mood='" + mood + '\'' +
                ", change='" + change + '\'' +
                ", user=" + user +
                '}';
    }
}
