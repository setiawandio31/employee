package com.example.employee.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.employee.model.dto.PendapatanDTO;
import com.example.employee.model.entity.Karyawan;
import com.example.employee.model.entity.Parameter;
import com.example.employee.model.entity.Pendapatan;
import com.example.employee.model.entity.PresentaseGaji;
import com.example.employee.model.entity.TunjanganPegawai;
import com.example.employee.repository.KaryawanRepository;
import com.example.employee.repository.ParameterRepository;
import com.example.employee.repository.PendapatanRepository;
import com.example.employee.repository.PenempatanRepository;
import com.example.employee.repository.PresentaseGajiRepository;
import com.example.employee.repository.TunjanganPegawaiRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculationPendapatan")
public class CalculatationPendapatanController {
    @Autowired
    PendapatanRepository pendapatanRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    PenempatanRepository penempatanRepository;

    @Autowired
    PresentaseGajiRepository presentaseGajiRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    TunjanganPegawaiRepository tunjanganPegawaiRepository;

    ModelMapper modelMapper = new ModelMapper();

    // Calculate Gaji Pokok
    public BigDecimal gajiPokok(Karyawan karyawan) {        
        BigDecimal precentaceGaji = null;

        List<Karyawan> listKaryawan = karyawanRepository.findAll();
        List<PresentaseGaji> listPresentaseGaji = presentaseGajiRepository.findAll(Sort.by(Sort.Direction.ASC, "masaKerja"));
        List<PresentaseGaji> listPresentasiByPosisiTingkatan = new ArrayList<>();

        for(PresentaseGaji presentaseGaji : listPresentaseGaji){
            if(presentaseGaji.getIdTingkatan() == karyawan.getTingkatan().getIdTingkatan() && presentaseGaji.getPosisi().getIdPosisi() == karyawan.getPosisi().getIdPosisi()){
                listPresentasiByPosisiTingkatan.add(presentaseGaji);
            }
        }

        int index = 1;
        System.out.println(listPresentasiByPosisiTingkatan.size());
        for ( PresentaseGaji presentaseGaji : listPresentasiByPosisiTingkatan ) {
            if ( presentaseGaji.getIdTingkatan() == karyawan.getTingkatan().getIdTingkatan() && presentaseGaji.getPosisi().getIdPosisi() == karyawan.getPosisi().getIdPosisi() ) {
                if (index < listPresentasiByPosisiTingkatan.size()) {// 1 0
                    if ( karyawan.getMasaKerja() >= 0 && karyawan.getMasaKerja() <= presentaseGaji.getMasaKerja()) {
                        precentaceGaji = presentaseGaji.getBesaranGaji();
                        index = listPresentaseGaji.size();
                    } else {
                        index++;
                    }
                      
                }
                else {
                    if (karyawan.getMasaKerja() >= presentaseGaji.getMasaKerja()){
                        precentaceGaji = presentaseGaji.getBesaranGaji();
                    }
                }         
            }
        }

        double gajiPokok = karyawan.getPenempatan().getUmkPenempatan().doubleValue() * precentaceGaji.doubleValue();

        return BigDecimal.valueOf(gajiPokok);
    }

    // Calculate Tunjangan Keluarga
    public BigDecimal tKeluarga(Karyawan karyawan, BigDecimal gajiPokok) {
        List<Parameter> listParameter = parameterRepository.findAll();
        double tKeluarga = 0;
        BigDecimal tKeluargaValOf = null;

        for ( Parameter parameter : listParameter ) {
            if ( karyawan.getStatusPernikahan() == 1 ) {
                tKeluarga = parameter.getTKeluarga().doubleValue() * gajiPokok.doubleValue();
            }
            else {
                tKeluarga = 0;
            }
        } 

        return BigDecimal.valueOf(tKeluarga);
    }

    // Calculate Tunjangan Pegawai
    public BigDecimal tPegawai (Karyawan karyawan) {
        List<TunjanganPegawai> listTunjanganPegawai = tunjanganPegawaiRepository.findAll();
        double tPegawai = 0;
        BigDecimal tPegawaiValOf = null;

        for ( TunjanganPegawai tunjanganPegawai : listTunjanganPegawai ) {
            if ( karyawan.getPosisi().getIdPosisi() == tunjanganPegawai.getPosisi().getIdPosisi() && karyawan.getTingkatan().getIdTingkatan() == tunjanganPegawai.getTingkatan().getIdTingkatan()) {
                tPegawai = tunjanganPegawai.getBesaranTujnaganPegawai().doubleValue();
            }
        }       

        return BigDecimal.valueOf(tPegawai);
    }

    // Calculate Tunjangan Transport
    public BigDecimal tTransport(Karyawan karyawan) {
        List<Parameter> listParameter = parameterRepository.findAll();
        double tPegawai = 0;
        BigDecimal tPegawaiValOf = null;

        for ( Parameter parameter : listParameter ) {
            if ( karyawan.getPenempatan().getKotaPenempatan().equalsIgnoreCase("Jakarta") ) {
                tPegawai = parameter.getTTransport().doubleValue();
            }
        }

        return BigDecimal.valueOf(tPegawai);
    }

    // Calculate Gaji Kotor
    public BigDecimal gajiKotor(BigDecimal gajiPokok, BigDecimal tKeluarga, BigDecimal tPegawai, BigDecimal tTransport) {
        double gajiKotor = 0;
        BigDecimal gajiKotorValOf = null;
        gajiKotor = gajiPokok.doubleValue() + tKeluarga.doubleValue() + tPegawai.doubleValue() + tTransport.doubleValue();
        
        return BigDecimal.valueOf(gajiKotor);
    }

    // Calculate pph perbulan
    public BigDecimal pphPerbulan() {
        return BigDecimal.valueOf(0);
    }

    // Calculate  BPJS
    public BigDecimal bpjs(BigDecimal gajiPokok) {
        List<Parameter> listParameters = parameterRepository.findAll();
        double bpjs = 0;
        BigDecimal bpjsValOf = null;

        for ( Parameter parameter : listParameters ) {
            bpjs = parameter.getPBpjs().doubleValue() * gajiPokok.doubleValue();
        }

        return BigDecimal.valueOf(bpjs);
    }

    // Calculate Gaji Bersih
    public BigDecimal gajiBersih(BigDecimal gajiKotor, BigDecimal bpjs , BigDecimal pphPerBulan) {
        double gajiBersih = 0;
        BigDecimal gajiBersihValOf = null;

        gajiBersih = gajiKotor.doubleValue() - bpjs.doubleValue() - pphPerBulan.doubleValue();

        return BigDecimal.valueOf(gajiBersih);
    }

    // lama lembur 
    public BigDecimal lamaLembur() {
        return BigDecimal.valueOf(0);
    }

    // uang lembur
    public BigDecimal uangLembur() {
        return BigDecimal.valueOf(0);
    }

    // variable bonus
    public BigDecimal variableBonus() {
        return BigDecimal.valueOf(0);
    }

    // uang bonus
    public BigDecimal uangBonus() {
        return BigDecimal.valueOf(0);
    }

    // take home pay
    public BigDecimal takeHomePay(BigDecimal gajiBersih, BigDecimal uangBonus, BigDecimal uangLembur){
        double takeHomePay = 0;

        takeHomePay = gajiBersih.doubleValue() + uangBonus.doubleValue() + uangLembur.doubleValue();
        // GajiBersih + Bonus + Lembur
        return BigDecimal.valueOf(takeHomePay);
    }

    @PostMapping("/date")
    public Map<String, Object> pendapatan(@RequestParam("date") String date) {

        String sDate1 = date;
        LocalDate myDate = LocalDate.parse(sDate1);
        Date dates = Date.from(myDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Map<String, Object> result = new HashMap<String, Object>();
        List<Karyawan> listKaryawan = karyawanRepository.findAll();
        List<PendapatanDTO> listPendapatanDTO = new ArrayList<PendapatanDTO>();
        List<Pendapatan> listPendapatan = pendapatanRepository.findAll(Sort.by(Sort.Direction.ASC, "idPendapatan"));
        int indexKaryawan = 0;

        for ( Karyawan karyawan : listKaryawan ) {
            List<PresentaseGaji> listPresentasiByPosisiTingkatan = new ArrayList<>();
            BigDecimal gajiPokok = null, tKeluarga = null, tTransport = null, tPegawai = null, gajiKotor = null, pBpjs = null, pphPerbulan = null, gajiBersih = null, lamaLembur = null, uangLembur = null, variableBonus = null, uangBonus = null, takeHomePay = null;
            BigDecimal umk = karyawan.getPenempatan().getUmkPenempatan();
            BigDecimal precentaceGaji = null;
            List<Integer> listMasaKerja = new ArrayList<>();

            gajiPokok = gajiPokok(karyawan);
            tKeluarga = tKeluarga(karyawan, gajiPokok);
            tPegawai = tPegawai(karyawan);
            tTransport = tTransport(karyawan);
            gajiKotor = gajiKotor(gajiPokok, tKeluarga, tPegawai, tTransport);
            pphPerbulan = pphPerbulan();
            pBpjs = bpjs(gajiPokok);
            gajiBersih = gajiBersih(gajiKotor, pBpjs, pphPerbulan);
            lamaLembur = lamaLembur();
            uangLembur = uangLembur();
            variableBonus = variableBonus();
            uangBonus = uangBonus();
            takeHomePay = takeHomePay(gajiBersih, uangBonus, uangLembur);

            Pendapatan pendapatan = new Pendapatan();
            pendapatan.setTanggalGaji(dates);
            pendapatan.setKaryawan(karyawan);
            pendapatan.setGajiPokok(gajiPokok);
            pendapatan.setTunjanganKeluarga(tKeluarga);
            pendapatan.setTunjanganPegawai(tPegawai);
            pendapatan.setTunjanganTransport(tTransport);
            pendapatan.setGajiKotor(gajiKotor);
            pendapatan.setPphPerbulan(pphPerbulan);
            pendapatan.setBpjs(pBpjs);
            pendapatan.setGajiBersih(gajiBersih);
            pendapatan.setLamaLembur(lamaLembur.intValue());
            pendapatan.setUangLembur(uangLembur);
            pendapatan.setVariableBonus(variableBonus.intValue());
            pendapatan.setUangBonus(uangBonus);
            pendapatan.setTakeHomePay(takeHomePay);

            PendapatanDTO pendapatanDTO = modelMapper.map(pendapatan, PendapatanDTO.class);
            System.out.println(pendapatanDTO.getTanggalGaji());
            listPendapatanDTO.add(pendapatanDTO);
            Boolean checkDuplicate = false;

            for ( int indexPendapatan = 0; indexPendapatan < listPendapatan.size(); indexPendapatan++ ) {
                if ( listPendapatan.get(indexPendapatan).getTanggalGaji().getMonth() == dates.getMonth() && listPendapatan.get(indexPendapatan).getTanggalGaji().getYear() == dates.getYear() ) {
                    if ( indexPendapatan == indexKaryawan ) {
                        pendapatan.setIdPendapatan(listPendapatan.get(indexPendapatan).getIdPendapatan());
                        pendapatanRepository.save(pendapatan);
                        checkDuplicate = true;
                        indexPendapatan = listPendapatan.size() + 1;
                    }
                }
            }

            if ( !checkDuplicate ) {
                pendapatanRepository.save(pendapatan);
            }
            indexKaryawan++;
        }

        result.put("status", 200);
        result.put("message", "Calculation Salary Success");
        result.put("data", listPendapatanDTO);

        return result;
    } 
}