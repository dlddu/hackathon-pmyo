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

    int score;
    String result_img;
    //날짜 필드
    LocalDate date;
    @Enumerated(EnumType.STRING)
    Category category;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecycleResult{" +
                "score=" + score +
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

@Repository interface RecycleResultRepository extends JpaRepository<RecycleResult, Long> {
}