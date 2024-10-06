package com.ytrewq.rosLearning.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    private String title;
    private Date dateOfCreation;
    private String description;
    private Theme[] themes;

    public Course(String title, Date dateOfCreation, String description, Theme[] themes) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.description = description;
        this.themes = themes;
    }

    protected Course() {
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "dateOfCreation")
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany
    @JoinColumn(name = "course_id")
    public Theme[] getThemes() {
        return themes;
    }

    public void setThemes(Theme[] themes) {
        this.themes = themes;
    }
}
