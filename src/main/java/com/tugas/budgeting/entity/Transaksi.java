package com.tugas.budgeting.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

// OOP: Entity dengan relasi ManyToOne ke Kategori
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double jumlah;

    private String keterangan;

    @Column(nullable = false)
    private LocalDate tanggal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kategori_id", nullable = false)
    private Kategori kategori;

    public Transaksi() {}

    public Transaksi(Double jumlah, String keterangan, LocalDate tanggal, Kategori kategori) {
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.kategori = kategori;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getJumlah() { return jumlah; }
    public void setJumlah(Double jumlah) { this.jumlah = jumlah; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }
}
