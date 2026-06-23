package com.tugas.budgeting.entity;

import jakarta.persistence.*;

// OOP: Entity untuk menyimpan budget bulanan
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer bulan;

    @Column(nullable = false)
    private Integer tahun;

    @Column(name = "jumlah_budget", nullable = false)
    private Double jumlahBudget;

    public Budget() {}

    public Budget(Integer bulan, Integer tahun, Double jumlahBudget) {
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlahBudget = jumlahBudget;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getBulan() { return bulan; }
    public void setBulan(Integer bulan) { this.bulan = bulan; }

    public Integer getTahun() { return tahun; }
    public void setTahun(Integer tahun) { this.tahun = tahun; }

    public Double getJumlahBudget() { return jumlahBudget; }
    public void setJumlahBudget(Double jumlahBudget) { this.jumlahBudget = jumlahBudget; }
}
