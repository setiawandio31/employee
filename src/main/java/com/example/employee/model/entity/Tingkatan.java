package com.example.employee.model.entity;
// Generated Nov 26, 2019, 10:07:59 AM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Tingkatan generated by hbm2java
 */
@Entity
@Table(name = "tingkatan", schema = "public")
public class Tingkatan implements java.io.Serializable {

	private long idTingkatan;
	private String namaTingkatan;
	private Set<TunjanganPegawai> tunjanganPegawais = new HashSet<TunjanganPegawai>(0);
	private Set<Karyawan> karyawans = new HashSet<Karyawan>(0);

	public Tingkatan() {
	}

	public Tingkatan(long idTingkatan, String namaTingkatan) {
		this.idTingkatan = idTingkatan;
		this.namaTingkatan = namaTingkatan;
	}

	public Tingkatan(long idTingkatan, String namaTingkatan, Set<TunjanganPegawai> tunjanganPegawais,
			Set<Karyawan> karyawans) {
		this.idTingkatan = idTingkatan;
		this.namaTingkatan = namaTingkatan;
		this.tunjanganPegawais = tunjanganPegawais;
		this.karyawans = karyawans;
	}

	@Id
	@SequenceGenerator(name="tingkatan_seq_gen", sequenceName="agama_id_seq", schema = "public", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tingkatan_seq_gen")
	@Column(name = "`ID_TINGKATAN`", unique = true, nullable = false)
	public long getIdTingkatan() {
		return this.idTingkatan;
	}

	public void setIdTingkatan(long idTingkatan) {
		this.idTingkatan = idTingkatan;
	}

	@Column(name = "`NAMA_TINGKATAN`", nullable = false, length = 128)
	public String getNamaTingkatan() {
		return this.namaTingkatan;
	}

	public void setNamaTingkatan(String namaTingkatan) {
		this.namaTingkatan = namaTingkatan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tingkatan")
	public Set<TunjanganPegawai> getTunjanganPegawais() {
		return this.tunjanganPegawais;
	}

	public void setTunjanganPegawais(Set<TunjanganPegawai> tunjanganPegawais) {
		this.tunjanganPegawais = tunjanganPegawais;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tingkatan")
	public Set<Karyawan> getKaryawans() {
		return this.karyawans;
	}

	public void setKaryawans(Set<Karyawan> karyawans) {
		this.karyawans = karyawans;
	}

}
