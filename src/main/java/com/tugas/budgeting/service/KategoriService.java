package com.tugas.budgeting.service;

import com.tugas.budgeting.entity.JenisTransaksi;
import com.tugas.budgeting.entity.Kategori;
import com.tugas.budgeting.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// OOP: Service layer memisahkan logika bisnis dari controller
@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    // Collection Framework - List
    public List<Kategori> getAll() {
        return kategoriRepository.findAll();
    }

    // Stream API + Lambda: filter hanya kategori PENGELUARAN
    public List<Kategori> getPengeluaran() {
        return kategoriRepository.findAll().stream()
                .filter(k -> k.getJenis() == JenisTransaksi.PENGELUARAN)
                .toList();
    }

    // Stream API + Lambda: filter hanya kategori PEMASUKAN
    public List<Kategori> getPemasukan() {
        return kategoriRepository.findAll().stream()
                .filter(k -> k.getJenis() == JenisTransaksi.PEMASUKAN)
                .toList();
    }

    public Optional<Kategori> getById(Long id) {
        return kategoriRepository.findById(id);
    }

    public Kategori save(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

    public Kategori update(Long id, Kategori data) {
        Kategori k = kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));
        k.setNama(data.getNama());
        k.setJenis(data.getJenis());
        return kategoriRepository.save(k);
    }

    public void delete(Long id) {
        kategoriRepository.deleteById(id);
    }
}
