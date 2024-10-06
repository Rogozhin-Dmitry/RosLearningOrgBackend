package com.ytrewq.rosLearning.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "themes")
public class Theme extends BaseEntity {
    private String title;
    private Date dateOfCreation;
    private String description;
    private Task[] tasks;
    private ThemeMaterial[] materials;

    public Theme(String title, Date dateOfCreation, String description, Task[] tasks, ThemeMaterial[] materials) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.description = description;
        this.tasks = tasks;
        this.materials = materials;
    }

    protected Theme() {
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
    @JoinColumn(name = "theme_id")
    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    @OneToMany
    @JoinColumn(name = "theme_id")
    public ThemeMaterial[] getMaterials() {
        return materials;
    }

    public void setMaterials(ThemeMaterial[] materials) {
        this.materials = materials;
    }
}
