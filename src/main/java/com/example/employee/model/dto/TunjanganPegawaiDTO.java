package com.example.employee.model.dto;

import java.math.BigDecimal;

import com.example.employee.model.entity.Posisi;
import com.example.employee.model.entity.Tingkatan;

public class TunjanganPegawaiDTO {
    private long idTunjanganPegawai;
	private Posisi posisi;
	private Tingkatan tingkatan;
    private BigDecimal besaranTujnaganPegawai;

    public long getIdTunjanganPegawai() {
        return idTunjanganPegawai;
    }

    public void setIdTunjanganPegawai(long idTunjanganPegawai) {
        this.idTunjanganPegawai = idTunjanganPegawai;
    }

    public Posisi getPosisi() {
        return posisi;
    }

    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }

    public Tingkatan getTingkatan() {
        return tingkatan;
    }

    public void setTingkatan(Tingkatan tingkatan) {
        this.tingkatan = tingkatan;
    }

    public BigDecimal getBesaranTujnaganPegawai() {
        return besaranTujnaganPegawai;
    }

    public void setBesaranTujnaganPegawai(BigDecimal besaranTujnaganPegawai) {
        this.besaranTujnaganPegawai = besaranTujnaganPegawai;
    }
    
}