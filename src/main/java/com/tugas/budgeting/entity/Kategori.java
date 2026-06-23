package com.tugas.budgeting.entity;

import jakarta.persistence.*;

// OOP: Entity class merepresentasikan tabel kategori
@Entity
@Table(name = "kategori")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nama;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JenisTransaksi jenis;

    public Kategori() {}

    public Kategori(String nama, JenisTransaksi jenis) {
        this.nama = nama;
        this.jenis = jenis;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public JenisTransaksi getJenis() { return jenis; }
    public void setJenis(JenisTransaksi jenis) { this.jenis = jenis; }
}
