package com.tugas.budgeting.service;

import com.tugas.budgeting.entity.Budget;
import com.tugas.budgeting.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// OOP: Service untuk manajemen budget bulanan
@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    // Collection Framework - List
    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getByBulanTahun(int bulan, int tahun) {
        return budgetRepository.findByBulanAndTahun(bulan, tahun);
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    // Simpan atau update budget bulan tertentu
    public Budget saveOrUpdate(Budget data) {
        Optional<Budget> existing = budgetRepository.findByBulanAndTahun(data.getBulan(), data.getTahun());
        if (existing.isPresent()) {
            Budget b = existing.get();
            b.setJumlahBudget(data.getJumlahBudget());
            return budgetRepository.save(b);
        }
        return budgetRepository.save(data);
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }

    // Hitung sisa budget bulan ini (budget - total pengeluaran)
    public Double getSisaBudget(int bulan, int tahun, Double totalPengeluaran) {
        return budgetRepository.findByBulanAndTahun(bulan, tahun)
                .map(b -> b.getJumlahBudget() - totalPengeluaran)
                .orElse(0.0);
    }
}
