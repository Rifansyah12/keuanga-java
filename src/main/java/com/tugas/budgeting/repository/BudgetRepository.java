package com.tugas.budgeting.repository;

import com.tugas.budgeting.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JPA Repository: cari budget berdasarkan bulan dan tahun tertentu
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByBulanAndTahun(Integer bulan, Integer tahun);
}
