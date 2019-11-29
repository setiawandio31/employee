package com.example.employee.model.dto;

import java.math.BigDecimal;

import com.example.employee.model.entity.Posisi;

public class PresentaseGajiDTO {
    private long idPresentaseGaji;
	private Posisi posisi;
	private Long idTingkatan;
	private BigDecimal besaranGaji;
    private Integer masaKerja;

    public long getIdPresentaseGaji() {
        return idPresentaseGaji;
    }

    public void setIdPresentaseGaji(long idPresentaseGaji) {
        this.idPresentaseGaji = idPresentaseGaji;
    }

    public Posisi getPosisi() {
        return posisi;
    }

    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }

    public Long getIdTingkatan() {
        return idTingkatan;
    }

    public void setIdTingkatan(Long idTingkatan) {
        this.idTingkatan = idTingkatan;
    }

    public BigDecimal getBesaranGaji() {
        return besaranGaji;
    }

    public void setBesaranGaji(BigDecimal besaranGaji) {
        this.besaranGaji = besaranGaji;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
    }
    
    
}