package com.ytrewq.rosLearning.Entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;
    private String name;
    private String role;
    private Date dateOfRegistration;
    private Boolean admin;
    private Result result;
    private Course[] courses;

    public User(String email, String name, String role, Date dateOfRegistration, Boolean admin, Result result, Course[] courses) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.dateOfRegistration = dateOfRegistration;
        this.admin = admin;
        this.result = result;
        this.courses = courses;
    }

    protected User() {
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "register_date")
    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "is_admin")
    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id", referencedColumnName = "id", nullable = false)
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }
}
