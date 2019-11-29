package com.example.employee.model.dto;

import com.example.employee.model.entity.Karyawan;
import com.example.employee.model.entity.Kemampuan;

public class ListKemampuanDTO {
    private long idListKemampuan;
	private Karyawan karyawan;
	private Kemampuan kemampuan;
    private Integer nilaiKemampuan;

    public long getIdListKemampuan() {
        return idListKemampuan;
    }

    public void setIdListKemampuan(long idListKemampuan) {
        this.idListKemampuan = idListKemampuan;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public Kemampuan getKemampuan() {
        return kemampuan;
    }

    public void setKemampuan(Kemampuan kemampuan) {
        this.kemampuan = kemampuan;
    }

    public Integer getNilaiKemampuan() {
        return nilaiKemampuan;
    }

    public void setNilaiKemampuan(Integer nilaiKemampuan) {
        this.nilaiKemampuan = nilaiKemampuan;
    }
    
    
}