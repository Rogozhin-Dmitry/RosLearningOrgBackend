package com.ytrewq.rosLearning.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


// TODO сделать эту таблицу в mongo
@Entity
@Table(name = "results")
public class Result extends BaseEntity {
    //    TODO это JSON
    private String result;
    private User user;

    public Result(String result) {
        this.result = result;
    }

    protected Result() {
    }

    @Column(name = "result", length = 5000)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @OneToOne(mappedBy = "result")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
