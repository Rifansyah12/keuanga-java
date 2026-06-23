package com.tugas.budgeting.controller;

import com.tugas.budgeting.entity.Budget;
import com.tugas.budgeting.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RESTful API: endpoint untuk budget bulanan
@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> getAll() {
        return ResponseEntity.ok(budgetService.getAll());
    }

    @GetMapping("/bulan/{bulan}/tahun/{tahun}")
    public ResponseEntity<Budget> getByBulanTahun(
            @PathVariable int bulan, @PathVariable int tahun) {
        return budgetService.getByBulanTahun(bulan, tahun)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Budget> saveOrUpdate(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.saveOrUpdate(budget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        budgetService.delete(id);
        return ResponseEntity.ok().build();
    }
}
