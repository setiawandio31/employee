package com.example.employee.model.dto;

public class KategoriKemampuanDTO {
    private long idKategori;
    private String namaKategori;

    public long getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(long idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public KategoriKemampuanDTO(long idKategori, String namaKategori) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
    }

    public KategoriKemampuanDTO() {
    }
    
}