package com.pmyo.pmyo.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class RecycleResult{
    int pass;
    int fail;
    int score;

    public RecycleResult(){}

    public int getScore() {
        return score;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "RecycleResult{" +
                "pass=" + pass +
                ", fail=" + fail +
                ", score=" + score +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }
}