package com.pmyo.pmyo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Repository
public interface RecycleResultRepository extends JpaRepository<RecycleResult, Long> {
    Iterable<RecycleResult> findByDateBetween(LocalDate start, LocalDate end);
}
