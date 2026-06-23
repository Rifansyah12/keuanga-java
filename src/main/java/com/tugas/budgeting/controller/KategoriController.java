package com.tugas.budgeting.controller;

import com.tugas.budgeting.entity.Kategori;
import com.tugas.budgeting.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RESTful API: endpoint CRUD untuk Kategori
@RestController
@RequestMapping("/api/kategori")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public ResponseEntity<List<Kategori>> getAll() {
        return ResponseEntity.ok(kategoriService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategori> getById(@PathVariable Long id) {
        return kategoriService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Kategori> create(@RequestBody Kategori kategori) {
        return ResponseEntity.ok(kategoriService.save(kategori));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kategori> update(@PathVariable Long id, @RequestBody Kategori kategori) {
        try {
            return ResponseEntity.ok(kategoriService.update(id, kategori));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kategoriService.delete(id);
        return ResponseEntity.ok().build();
    }
}
