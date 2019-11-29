package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.KategoriKemampuanDTO;
import com.example.employee.model.entity.KategoriKemampuan;
import com.example.employee.repository.KategoriKemampuanRepository;

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
@RequestMapping("/api/kategoriKemampuan")
public class KategoriKemampuanController {
    @Autowired
    KategoriKemampuanRepository kategoriKemampuanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<KategoriKemampuan> listKategoriKemampuanEntity = kategoriKemampuanRepository.findAll();
        List<KategoriKemampuanDTO> listKategoriKemampuanDTO = new ArrayList<KategoriKemampuanDTO>();

        for ( KategoriKemampuan kategoriKemampuan : listKategoriKemampuanEntity ) {
            KategoriKemampuanDTO kategoriKemampuanDTO = modelMapper.map(kategoriKemampuan, KategoriKemampuanDTO.class);
            listKategoriKemampuanDTO.add(kategoriKemampuanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listKategoriKemampuanDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        KategoriKemampuan kategoriKemampuan = kategoriKemampuanRepository.findById(id).orElseThrow();
        KategoriKemampuanDTO kategoriKemampuanDTO = modelMapper.map(kategoriKemampuan, KategoriKemampuanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", kategoriKemampuanDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody KategoriKemampuanDTO kategoriKemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        KategoriKemampuan kategoriKemampuan = modelMapper.map(kategoriKemampuanDTO, KategoriKemampuan.class);
        
        kategoriKemampuanRepository.save(kategoriKemampuan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", kategoriKemampuanDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody KategoriKemampuanDTO kategoriKemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        KategoriKemampuan kategoriKemampuan = modelMapper.map(kategoriKemampuanDTO, KategoriKemampuan.class);
        kategoriKemampuan.setIdKategori(id);

        kategoriKemampuanRepository.save(kategoriKemampuan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", kategoriKemampuanDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        KategoriKemampuan kategoriKemampuan = kategoriKemampuanRepository.findById(id).orElseThrow();
        
        kategoriKemampuanRepository.delete(kategoriKemampuan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}