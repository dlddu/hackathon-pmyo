package com.pmyo.pmyo.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class RecycleResult{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int pass;
    int fail;
    int score;
    String result_img;
    //날짜 필드
    LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecycleResult{" +
                "pass=" + pass +
                ", fail=" + fail +
                ", score=" + score +
                ", result_img='" + result_img + '\'' +
                '}';
    }


    public String getResult_img() {
        return result_img;
    }

    public void setResult_img(String result_img) {
        this.result_img = result_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
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

@Repository interface RecycleResultRepository extends JpaRepository<RecycleResult, Long> {
}