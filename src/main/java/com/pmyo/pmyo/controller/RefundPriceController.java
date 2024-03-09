package com.pmyo.pmyo.controller;

import co.elastic.clients.util.DateTime;
import com.pmyo.pmyo.model.RecycleResult;
import com.pmyo.pmyo.model.RecycleResultRepository;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping
public class RefundPriceController {
    @Autowired private RecycleResultRepository recycleResultRepository;
    @GetMapping("/refund-price")
    public Long getRefundPrice() {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        Iterable<RecycleResult> recycleResult = recycleResultRepository.findByDateBetween(start, end);
        return IterableUtils.toList(recycleResult).stream().mapToLong(RecycleResult::getScore).sum() * 150 / 100;
    }
}
