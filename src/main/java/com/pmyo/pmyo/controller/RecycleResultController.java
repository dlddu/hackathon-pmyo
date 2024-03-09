package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.model.RecycleResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

public class RecycleResultController{

    @GetMapping("/server/imgList")
    public String showRecycleForm() {
        return "recycleForm";
    }

    

    @PostMapping("/gpts")
    public ResponseEntity<RecycleResult> showRecycleResult(@RequestParam("score") int score,
                                                           @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        RecycleResult result = new RecycleResult();
        result.setScore(score);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}