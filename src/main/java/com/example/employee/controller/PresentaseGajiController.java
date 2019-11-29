package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.PresentaseGajiDTO;
import com.example.employee.model.entity.PresentaseGaji;
import com.example.employee.repository.PresentaseGajiRepository;

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
@RequestMapping("/api/presentaseGaji")
public class PresentaseGajiController {
    @Autowired
    PresentaseGajiRepository presentaseGajiRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<PresentaseGaji> listPresentaseGajiEntity = presentaseGajiRepository.findAll();
        List<PresentaseGajiDTO> listPresentaseGajiDTO = new ArrayList<PresentaseGajiDTO>();

        for ( PresentaseGaji presentaseGaji : listPresentaseGajiEntity ) {
            PresentaseGajiDTO presentaseGajiDTO = modelMapper.map(presentaseGaji, PresentaseGajiDTO.class);
            listPresentaseGajiDTO.add(presentaseGajiDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listPresentaseGajiDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        PresentaseGaji presentaseGaji = presentaseGajiRepository.findById(id).orElseThrow();
        PresentaseGajiDTO presentaseGajiDTO = modelMapper.map(presentaseGaji, PresentaseGajiDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", presentaseGajiDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody PresentaseGajiDTO presentaseGajiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        PresentaseGaji presentaseGaji = modelMapper.map(presentaseGajiDTO, PresentaseGaji.class);
        
        presentaseGajiRepository.save(presentaseGaji);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", presentaseGajiDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PresentaseGajiDTO presentaseGajiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        PresentaseGaji presentaseGaji = modelMapper.map(presentaseGajiDTO, PresentaseGaji.class);
        presentaseGaji.setIdPresentaseGaji(id);

        presentaseGajiRepository.save(presentaseGaji);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", presentaseGajiDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        PresentaseGaji presentaseGaji = presentaseGajiRepository.findById(id).orElseThrow();
        
        presentaseGajiRepository.delete(presentaseGaji);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}