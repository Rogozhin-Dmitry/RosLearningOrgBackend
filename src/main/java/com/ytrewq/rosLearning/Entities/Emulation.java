package com.ytrewq.rosLearning.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "emulations")
public class Emulation extends BaseEntity {
    private String private_title;
    private Date dateOfCreation;
    private Integer timerTime;
    private String timerDescription;
    private String screenImageURL;
    private String blockSchemeJSON;
    private String blockCodeJS;
    private String byteArrayInterface;
    private Task task;

    public Emulation(String private_title, Date dateOfCreation, Integer timerTime, String timerDescription, String screenImageURL, String blockSchemeJSON, String blockCodeJS, String byteArrayInterface, Task task) {
        this.private_title = private_title;
        this.dateOfCreation = dateOfCreation;
        this.timerTime = timerTime;
        this.timerDescription = timerDescription;
        this.screenImageURL = screenImageURL;
        this.blockSchemeJSON = blockSchemeJSON;
        this.blockCodeJS = blockCodeJS;
        this.byteArrayInterface = byteArrayInterface;
        this.task = task;
    }

    protected Emulation() {
    }

    @Column(name = "private_name", unique = true)
    public String getPrivate_title() {
        return private_title;
    }

    public void setPrivate_title(String private_title) {
        this.private_title = private_title;
    }

    @Column(name = "dateOfCreation")
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Column(name = "timerTime")
    public Integer getTimerTime() {
        return timerTime;
    }

    public void setTimerTime(Integer timerTime) {
        this.timerTime = timerTime;
    }

    @Column(name = "timerDescription")
    public String getTimerDescription() {
        return timerDescription;
    }

    public void setTimerDescription(String timerDescription) {
        this.timerDescription = timerDescription;
    }

    @Column(name = "screenImageURL")
    public String getScreenImageURL() {
        return screenImageURL;
    }

    public void setScreenImageURL(String screenImageURL) {
        this.screenImageURL = screenImageURL;
    }

    @Column(name = "blockSchemeJSON", length=32768)
    public String getBlockSchemeJSON() {
        return blockSchemeJSON;
    }

    public void setBlockSchemeJSON(String blockSchemeJSON) {
        this.blockSchemeJSON = blockSchemeJSON;
    }

    @Column(name = "blockCodeJS", length=32768)
    public String getBlockCodeJS() {
        return blockCodeJS;
    }

    public void setBlockCodeJS(String blockCodeJS) {
        this.blockCodeJS = blockCodeJS;
    }

    @Column(name = "byteArrayInterface", length=32768)
    public String getByteArrayInterface() {
        return byteArrayInterface;
    }

    public void setByteArrayInterface(String byteArrayInterface) {
        this.byteArrayInterface = byteArrayInterface;
    }

    @OneToOne(mappedBy = "emulation")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}