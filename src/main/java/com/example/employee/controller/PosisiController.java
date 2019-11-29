package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.PosisiDTO;
import com.example.employee.model.entity.Posisi;
import com.example.employee.repository.PosisiRepository;

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
@RequestMapping("/api/posisi")
public class PosisiController {
    @Autowired
    PosisiRepository posisiRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Posisi> listPosisiEntity = posisiRepository.findAll();
        List<PosisiDTO> listPosisiDTO = new ArrayList<PosisiDTO>();

        for ( Posisi posisi : listPosisiEntity ) {
            PosisiDTO posisiDTO = modelMapper.map(posisi, PosisiDTO.class);
            listPosisiDTO.add(posisiDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listPosisiDTO);

        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Posisi posisi = posisiRepository.findById(id).orElseThrow();
        PosisiDTO posisiDTO = modelMapper.map(posisi, PosisiDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", posisiDTO);

        return result;
    }

    @PostMapping()
    public Map<String, Object> create(@Valid @RequestBody PosisiDTO posisiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Posisi posisi = modelMapper.map(posisiDTO, Posisi.class);
        
        posisiRepository.save(posisi);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", posisiDTO);
        
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PosisiDTO posisiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Posisi posisi = modelMapper.map(posisiDTO, Posisi.class);
        posisi.setIdPosisi(id);

        posisiRepository.save(posisi);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", posisiDTO);

        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Posisi posisi = posisiRepository.findById(id).orElseThrow();
        
        posisiRepository.delete(posisi);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}