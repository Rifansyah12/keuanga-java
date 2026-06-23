package com.tugas.budgeting.controller;

import com.tugas.budgeting.entity.Transaksi;
import com.tugas.budgeting.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

// RESTful API: CRUD transaksi + endpoint analisis menggunakan Stream API
@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public ResponseEntity<List<Transaksi>> getAll() {
        return ResponseEntity.ok(transaksiService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getById(@PathVariable Long id) {
        return transaksiService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaksi> create(@RequestBody Transaksi transaksi) {
        return ResponseEntity.ok(transaksiService.save(transaksi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaksi> update(@PathVariable Long id, @RequestBody Transaksi transaksi) {
        try {
            return ResponseEntity.ok(transaksiService.update(id, transaksi));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transaksiService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Stream API: filter transaksi per bulan
    @GetMapping("/bulan/{bulan}/tahun/{tahun}")
    public ResponseEntity<List<Transaksi>> getByBulan(
            @PathVariable int bulan, @PathVariable int tahun) {
        return ResponseEntity.ok(transaksiService.getByBulan(bulan, tahun));
    }

    // Stream API + Map: pengeluaran dikelompokkan per kategori
    @GetMapping("/per-kategori/bulan/{bulan}/tahun/{tahun}")
    public ResponseEntity<Map<String, Double>> getPerKategori(
            @PathVariable int bulan, @PathVariable int tahun) {
        return ResponseEntity.ok(transaksiService.getPengeluaranPerKategori(bulan, tahun));
    }

    // Stream API + Set: semua tahun yang ada transaksinya
    @GetMapping("/tahun")
    public ResponseEntity<Set<Integer>> getAllTahun() {
        return ResponseEntity.ok(transaksiService.getAllTahun());
    }
}
