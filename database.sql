-- ============================================================
-- DATABASE: db_budgeting
-- Aplikasi: CatatKeu - Catatan Keuangan Pribadi
-- Dibuat dengan Spring Boot 3 + JPA (MySQL)
-- ============================================================

CREATE DATABASE IF NOT EXISTS db_budgeting
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE db_budgeting;

-- ─── TABEL 1: kategori ───────────────────────────────────────
CREATE TABLE IF NOT EXISTS kategori (
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    nama  VARCHAR(255) NOT NULL UNIQUE,
    jenis VARCHAR(50)  NOT NULL COMMENT 'PEMASUKAN atau PENGELUARAN',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ─── TABEL 2: transaksi ──────────────────────────────────────
CREATE TABLE IF NOT EXISTS transaksi (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    jumlah       DOUBLE       NOT NULL,
    keterangan   VARCHAR(255),
    tanggal      DATE         NOT NULL,
    kategori_id  BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transaksi_kategori
        FOREIGN KEY (kategori_id) REFERENCES kategori(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ─── TABEL 3: budget ─────────────────────────────────────────
CREATE TABLE IF NOT EXISTS budget (
    id             BIGINT NOT NULL AUTO_INCREMENT,
    bulan          INT    NOT NULL COMMENT '1-12',
    tahun          INT    NOT NULL,
    jumlah_budget  DOUBLE NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ─── SAMPLE DATA: Kategori Default ───────────────────────────
INSERT INTO kategori (nama, jenis) VALUES
    ('Gaji',          'PEMASUKAN'),
    ('Freelance',     'PEMASUKAN'),
    ('Bonus',         'PEMASUKAN'),
    ('Makan',         'PENGELUARAN'),
    ('Transport',     'PENGELUARAN'),
    ('Belanja',       'PENGELUARAN'),
    ('Tagihan',       'PENGELUARAN'),
    ('Hiburan',       'PENGELUARAN'),
    ('Kesehatan',     'PENGELUARAN'),
    ('Pendidikan',    'PENGELUARAN');

-- ─── SAMPLE DATA: Budget Bulan Ini ───────────────────────────
INSERT INTO budget (bulan, tahun, jumlah_budget) VALUES
    (6, 2026, 3000000);

-- ─── SAMPLE DATA: Transaksi ──────────────────────────────────
INSERT INTO transaksi (jumlah, keterangan, tanggal, kategori_id) VALUES
    (5000000,  'Gaji bulan Juni',           '2026-06-01', 1),
    (500000,   'Freelance desain logo',     '2026-06-03', 2),
    (35000,    'Makan siang warteg',        '2026-06-04', 4),
    (50000,    'Bensin motor',              '2026-06-05', 5),
    (250000,   'Belanja bulanan',           '2026-06-07', 6),
    (150000,   'Tagihan listrik',           '2026-06-10', 7),
    (80000,    'Nonton bioskop',            '2026-06-12', 8),
    (75000,    'Obat dan vitamin',          '2026-06-14', 9),
    (25000,    'Makan pagi',                '2026-06-15', 4),
    (45000,    'Grab ke kantor',            '2026-06-16', 5);
