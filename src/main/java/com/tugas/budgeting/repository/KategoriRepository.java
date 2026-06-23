package com.tugas.budgeting.repository;

import com.tugas.budgeting.entity.JenisTransaksi;
import com.tugas.budgeting.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JPA Repository: CRUD otomatis + query method berdasarkan nama field
@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    List<Kategori> findByJenis(JenisTransaksi jenis);
    boolean existsByNama(String nama);
}
