package com.example.employee.model.dto;

import java.math.BigDecimal;

public class PenempatanDTO {
    private long idPenempatan;
	private String kotaPenempatan;
    private BigDecimal umkPenempatan;

    public long getIdPenempatan() {
        return idPenempatan;
    }

    public void setIdPenempatan(long idPenempatan) {
        this.idPenempatan = idPenempatan;
    }

    public String getKotaPenempatan() {
        return kotaPenempatan;
    }

    public void setKotaPenempatan(String kotaPenempatan) {
        this.kotaPenempatan = kotaPenempatan;
    }

    public BigDecimal getUmkPenempatan() {
        return umkPenempatan;
    }

    public void setUmkPenempatan(BigDecimal umkPenempatan) {
        this.umkPenempatan = umkPenempatan;
    }

}