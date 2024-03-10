package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.model.Category;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin
public class DashboardController {
    @Autowired
    private RecycleResultRepository recycleResultRepository;

    @GetMapping("/dashboard")
    public Response dashboard() {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        List<RecycleResult> recycleResult = IterableUtils.toList(recycleResultRepository.findByDateBetween(start, end));

        Map<Category, Long> chart = Arrays.stream(Category.values()).map(category -> {
            List<RecycleResult> categorizedResults = recycleResult.stream().filter(result -> result.getCategory() == category).toList();
            long score = categorizedResults.isEmpty() ? 0 : (categorizedResults.size() * 100L - categorizedResults.stream().mapToLong(RecycleResult::getScore).sum()) / categorizedResults.size();
            return Map.entry(category, score);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        long successRate = recycleResult.stream().filter(recycleResult1 -> recycleResult1.getScore() == 100).toList().size() * 100L / recycleResult.size();

        Response response = new Response();
        response.setChart(chart);
        response.setSuccessRate(successRate);
        return response;
    }

    public class Response {
        private Map<Category, Long> chart;
        private Long successRate;

        public Long getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(Long successRate) {
            this.successRate = successRate;
        }

        public Map<Category, Long> getChart() {
            return chart;
        }

        public void setChart(Map<Category, Long> chart) {
            this.chart = chart;
        }
    }
}
