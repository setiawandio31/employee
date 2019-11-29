package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.KemampuanDTO;
import com.example.employee.model.entity.Kemampuan;
import com.example.employee.repository.KemampuanRepository;

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
@RequestMapping("/api/kemampuan")
public class KemampuanController {
    @Autowired
    KemampuanRepository kemampuanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Kemampuan> listKemampuanEntity = kemampuanRepository.findAll();
        List<KemampuanDTO> listKemampuanDTO = new ArrayList<KemampuanDTO>();

        for ( Kemampuan kemampuan : listKemampuanEntity ) {
            KemampuanDTO kemampuanDTO = modelMapper.map(kemampuan, KemampuanDTO.class);
            listKemampuanDTO.add(kemampuanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listKemampuanDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Kemampuan kemampuan = kemampuanRepository.findById(id).orElseThrow();
        KemampuanDTO kemampuanDTO = modelMapper.map(kemampuan, KemampuanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", kemampuanDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody KemampuanDTO kemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Kemampuan kemampuan = modelMapper.map(kemampuanDTO, Kemampuan.class);
        
        kemampuanRepository.save(kemampuan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", kemampuanDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody KemampuanDTO kemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Kemampuan kemampuan = modelMapper.map(kemampuanDTO, Kemampuan.class);
        kemampuan.setIdKemampuan(id);

        kemampuanRepository.save(kemampuan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", kemampuanDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Kemampuan kemampuan = kemampuanRepository.findById(id).orElseThrow();
        
        kemampuanRepository.delete(kemampuan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}