package com.tugas.budgeting.service;

import com.tugas.budgeting.entity.JenisTransaksi;
import com.tugas.budgeting.entity.Transaksi;
import com.tugas.budgeting.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

// OOP: Service layer dengan implementasi Stream API dan Lambda Expression
@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    // Collection Framework - List
    public List<Transaksi> getAll() {
        return transaksiRepository.findAllByOrderByTanggalDesc();
    }

    public Optional<Transaksi> getById(Long id) {
        return transaksiRepository.findById(id);
    }

    public Transaksi save(Transaksi transaksi) {
        return transaksiRepository.save(transaksi);
    }

    public Transaksi update(Long id, Transaksi data) {
        Transaksi t = transaksiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));
        t.setJumlah(data.getJumlah());
        t.setKeterangan(data.getKeterangan());
        t.setTanggal(data.getTanggal());
        t.setKategori(data.getKategori());
        return transaksiRepository.save(t);
    }

    public void delete(Long id) {
        transaksiRepository.deleteById(id);
    }

    // Stream API + Lambda: filter transaksi berdasarkan bulan & tahun
    public List<Transaksi> getByBulan(int bulan, int tahun) {
        LocalDate awal = YearMonth.of(tahun, bulan).atDay(1);
        LocalDate akhir = YearMonth.of(tahun, bulan).atEndOfMonth();
        return transaksiRepository.findByTanggalBetween(awal, akhir);
    }

    // Stream API: hitung total pengeluaran bulan tertentu
    public Double getTotalPengeluaranBulan(int bulan, int tahun) {
        return getByBulan(bulan, tahun).stream()
                .filter(t -> t.getKategori().getJenis() == JenisTransaksi.PENGELUARAN)  // Lambda
                .mapToDouble(Transaksi::getJumlah)       // Method reference
                .sum();
    }

    // Stream API: hitung total pemasukan bulan tertentu
    public Double getTotalPemasukanBulan(int bulan, int tahun) {
        return getByBulan(bulan, tahun).stream()
                .filter(t -> t.getKategori().getJenis() == JenisTransaksi.PEMASUKAN)    // Lambda
                .mapToDouble(Transaksi::getJumlah)
                .sum();
    }

    // Stream API + Map (Collection Framework): total pengeluaran dikelompokkan per kategori
    public Map<String, Double> getPengeluaranPerKategori(int bulan, int tahun) {
        return getByBulan(bulan, tahun).stream()
                .filter(t -> t.getKategori().getJenis() == JenisTransaksi.PENGELUARAN)
                .collect(Collectors.groupingBy(
                        t -> t.getKategori().getNama(),      // Lambda Expression
                        Collectors.summingDouble(Transaksi::getJumlah)
                ));
    }

    // Stream API + Map: pemasukan vs pengeluaran per bulan (untuk grafik bar)
    public List<Map<String, Object>> getRingkasanPerBulan(int tahun) {
        List<Map<String, Object>> hasil = new ArrayList<>();
        for (int b = 1; b <= 12; b++) {
            int bulanFinal = b;
            Map<String, Object> row = new HashMap<>();
            row.put("bulan", b);
            row.put("pemasukan", getTotalPemasukanBulan(bulanFinal, tahun));
            row.put("pengeluaran", getTotalPengeluaranBulan(bulanFinal, tahun));
            hasil.add(row);
        }
        return hasil;
    }

    // Stream API + Set (Collection Framework): ambil semua tahun yang ada transaksinya
    public Set<Integer> getAllTahun() {
        return transaksiRepository.findAll().stream()
                .map(t -> t.getTanggal().getYear())   // Lambda / Method ref
                .collect(Collectors.toSet());           // Collect ke Set
    }
}
