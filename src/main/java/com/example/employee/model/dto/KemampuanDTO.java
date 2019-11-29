package com.example.employee.model.dto;

import com.example.employee.model.entity.KategoriKemampuan;

public class KemampuanDTO {
    private long idKemampuan;
	private KategoriKemampuan kategoriKemampuan;
    private String namaKemampuan;

    public long getIdKemampuan() {
        return idKemampuan;
    }

    public void setIdKemampuan(long idKemampuan) {
        this.idKemampuan = idKemampuan;
    }

    public KategoriKemampuan getKategoriKemampuan() {
        return kategoriKemampuan;
    }

    public void setKategoriKemampuan(KategoriKemampuan kategoriKemampuan) {
        this.kategoriKemampuan = kategoriKemampuan;
    }

    public String getNamaKemampuan() {
        return namaKemampuan;
    }

    public void setNamaKemampuan(String namaKemampuan) {
        this.namaKemampuan = namaKemampuan;
    }
    
    
}