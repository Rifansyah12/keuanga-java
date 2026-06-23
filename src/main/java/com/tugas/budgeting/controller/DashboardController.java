package com.tugas.budgeting.controller;

import com.tugas.budgeting.service.BudgetService;
import com.tugas.budgeting.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// RESTful API: agregasi data untuk dashboard & grafik
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private TransaksiService transaksiService;

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/bulan/{bulan}/tahun/{tahun}")
    public ResponseEntity<Map<String, Object>> getDashboard(
            @PathVariable int bulan, @PathVariable int tahun) {

        double pengeluaran = transaksiService.getTotalPengeluaranBulan(bulan, tahun);
        double pemasukan   = transaksiService.getTotalPemasukanBulan(bulan, tahun);
        double sisa        = budgetService.getSisaBudget(bulan, tahun, pengeluaran);

        Map<String, Object> data = new HashMap<>();
        data.put("totalPengeluaran", pengeluaran);
        data.put("totalPemasukan", pemasukan);
        data.put("sisaBudget", sisa);
        data.put("selisih", pemasukan - pengeluaran);

        // Stream API + Map: untuk grafik pie (pengeluaran per kategori)
        data.put("pengeluaranPerKategori", transaksiService.getPengeluaranPerKategori(bulan, tahun));

        // Stream API: untuk grafik bar (12 bulan dalam 1 tahun)
        data.put("ringkasanPerBulan", transaksiService.getRingkasanPerBulan(tahun));

        return ResponseEntity.ok(data);
    }
}
