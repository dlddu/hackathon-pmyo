package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.model.RecycleResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class RecycleResultController{

    @GetMapping("/server/imgList")
    public String showRecycleForm() {
        return "recycleForm";
    }

    @PostMapping("/gpts")
    public String calculateRecycleResult(@RequestParam("pass") int pass,
                                         @RequestParam("fail") int fail,
                                         @RequestParam("score") int score,
                                         Model model) {
        RecycleResult result = new RecycleResult();
        result.setPass(pass);
        result.setFail(fail);
        result.setScore(score);

        model.addAttribute("result", result);
        return "recycleResult";
    }
}