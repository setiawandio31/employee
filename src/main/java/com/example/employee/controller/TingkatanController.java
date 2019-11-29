package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.TingkatanDTO;
import com.example.employee.model.entity.Tingkatan;
import com.example.employee.repository.TingkatanRepository;

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
@RequestMapping("/api/tingkatan")
public class TingkatanController {
    @Autowired
    TingkatanRepository tingkatanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Tingkatan> listTingkatanEntity = tingkatanRepository.findAll();
        List<TingkatanDTO> listTingkatanDTO = new ArrayList<TingkatanDTO>();

        for ( Tingkatan tingkatan : listTingkatanEntity ) {
            TingkatanDTO tingkatanDTO = modelMapper.map(tingkatan, TingkatanDTO.class);
            listTingkatanDTO.add(tingkatanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listTingkatanDTO);

        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Tingkatan tingkatan = tingkatanRepository.findById(id).orElseThrow();
        TingkatanDTO tingkatanDTO = modelMapper.map(tingkatan, TingkatanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", tingkatanDTO);

        return result;
    }

    @PostMapping()
    public Map<String, Object> create(@Valid @RequestBody TingkatanDTO tingkatanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Tingkatan tingkatan = modelMapper.map(tingkatanDTO, Tingkatan.class);
        
        tingkatanRepository.save(tingkatan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", tingkatanDTO);
        
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TingkatanDTO tingkatanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Tingkatan tingkatan = modelMapper.map(tingkatanDTO, Tingkatan.class);
        tingkatan.setIdTingkatan(id);

        tingkatanRepository.save(tingkatan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", tingkatanDTO);

        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Tingkatan tingkatan = tingkatanRepository.findById(id).orElseThrow();
        
        tingkatanRepository.delete(tingkatan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}