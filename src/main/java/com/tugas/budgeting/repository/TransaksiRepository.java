package com.tugas.budgeting.repository;

import com.tugas.budgeting.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// JPA Repository: query method untuk filter transaksi berdasarkan tanggal
@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    List<Transaksi> findByTanggalBetween(LocalDate start, LocalDate end);
    List<Transaksi> findByKategoriId(Long kategoriId);
    List<Transaksi> findAllByOrderByTanggalDesc();
}
