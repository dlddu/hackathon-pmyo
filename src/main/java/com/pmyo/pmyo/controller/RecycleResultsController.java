package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.model.RecycleResult;
import com.pmyo.pmyo.model.RecycleResultRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("server/imgList")
@CrossOrigin
public class RecycleResultsController {
    @Autowired
    private RecycleResultRepository recycleResultRepository;

    @GetMapping
    public Iterable<String> recycleResultUrls() {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        Iterable<RecycleResult> recycleResult = recycleResultRepository.findByDateBetween(start, end);

        return IterableUtils.toList(recycleResult).stream().map(RecycleResult::getResult_img).toList();
    }
}
