package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.KaryawanDTO;
import com.example.employee.model.entity.Karyawan;
import com.example.employee.repository.KaryawanRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {
    @Autowired
    KaryawanRepository karyawanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Karyawan> listKaryawanEntity = karyawanRepository.findAll();
        List<KaryawanDTO> listKaryawanDTO = new ArrayList<KaryawanDTO>();

        for ( Karyawan karyawan : listKaryawanEntity ) {
            KaryawanDTO karyawanDTO = modelMapper.map(karyawan, KaryawanDTO.class);
            listKaryawanDTO.add(karyawanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listKaryawanDTO);

        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();
        KaryawanDTO karyawanDTO = modelMapper.map(karyawan, KaryawanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", karyawanDTO);

        return result;
    }

    @PostMapping()
    public Map<String, Object> create(@Valid @RequestBody KaryawanDTO karyawanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Karyawan karyawan = modelMapper.map(karyawanDTO, Karyawan.class);
        
        karyawanRepository.save(karyawan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", karyawanDTO);
        
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody KaryawanDTO karyawanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Karyawan karyawan = modelMapper.map(karyawanDTO, Karyawan.class);
        karyawan.setIdKaryawan(id);

        karyawanRepository.save(karyawan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", karyawanDTO);

        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();
        
        karyawanRepository.delete(karyawan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}