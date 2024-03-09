package com.pmyo.pmyo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class RecycleResultController{

    @GetMapping("/server/imgList")
    public String showRecycleForm() {
        return "recycleForm";
    }

    @PostMapping("/gpts")
    public String calculateRecycleResult(@RequestParam("pass") int pass,
                                         @RequestParam("fail") int fail,
                                         Model model) {
        RecycleResult result = new RecycleResult();
        result.setPass(pass);
        result.setFail(fail);
        result.setScore(score);

        model.addAttribute("result", result);
        return "recycleResult";
    }
}