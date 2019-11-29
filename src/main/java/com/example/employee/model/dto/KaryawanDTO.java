package com.example.employee.model.dto;

import java.util.Date;

public class KaryawanDTO {
    private long idKaryawan;
	private AgamaDTO agama;
	private PenempatanDTO penempatan;
	private PosisiDTO posisi;
	private TingkatanDTO tingkatan;
	private String nama;
	private String noKtp;
	private String alamat;
	private Date tanggalLahir;
	private Integer masaKerja;
	private Short statusPernikahan;
	private Date kontrakAwal;
	private Date kontrakAkhir;
	private String jenisKelamin;
    private Integer jumlahAnak;

    public long getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public AgamaDTO getAgama() {
        return agama;
    }

    public void setAgama(AgamaDTO agama) {
        this.agama = agama;
    }

    public PenempatanDTO getPenempatan() {
        return penempatan;
    }

    public void setPenempatan(PenempatanDTO penempatan) {
        this.penempatan = penempatan;
    }

    public PosisiDTO getPosisi() {
        return posisi;
    }

    public void setPosisi(PosisiDTO posisi) {
        this.posisi = posisi;
    }

    public TingkatanDTO getTingkatan() {
        return tingkatan;
    }

    public void setTingkatan(TingkatanDTO tingkatan) {
        this.tingkatan = tingkatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
    }

    public Short getStatusPernikahan() {
        return statusPernikahan;
    }

    public void setStatusPernikahan(Short statusPernikahan) {
        this.statusPernikahan = statusPernikahan;
    }

    public Date getKontrakAwal() {
        return kontrakAwal;
    }

    public void setKontrakAwal(Date kontrakAwal) {
        this.kontrakAwal = kontrakAwal;
    }

    public Date getKontrakAkhir() {
        return kontrakAkhir;
    }

    public void setKontrakAkhir(Date kontrakAkhir) {
        this.kontrakAkhir = kontrakAkhir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }
    
    
}