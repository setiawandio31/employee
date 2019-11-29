package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.PenempatanDTO;
import com.example.employee.model.entity.Penempatan;
import com.example.employee.repository.PenempatanRepository;

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
@RequestMapping("/api/penempatan")
public class PenempatanController {
    @Autowired
    PenempatanRepository penempatanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Penempatan> listPenempatanEntity = penempatanRepository.findAll();
        List<PenempatanDTO> listPenempatanDTO = new ArrayList<PenempatanDTO>();

        for ( Penempatan penempatan : listPenempatanEntity ) {
            PenempatanDTO penempatanDTO = modelMapper.map(penempatan, PenempatanDTO.class);
            listPenempatanDTO.add(penempatanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listPenempatanDTO);

        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Penempatan penempatan = penempatanRepository.findById(id).orElseThrow();
        PenempatanDTO penempatanDTO = modelMapper.map(penempatan, PenempatanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", penempatanDTO);

        return result;
    }

    @PostMapping()
    public Map<String, Object> create(@Valid @RequestBody PenempatanDTO penempatanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Penempatan penempatan = modelMapper.map(penempatanDTO, Penempatan.class);
        
        penempatanRepository.save(penempatan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", penempatanDTO);
        
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PenempatanDTO penempatanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Penempatan penempatan = modelMapper.map(penempatanDTO, Penempatan.class);
        penempatan.setIdPenempatan(id);

        penempatanRepository.save(penempatan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", penempatanDTO);

        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Penempatan penempatan = penempatanRepository.findById(id).orElseThrow();
        
        penempatanRepository.delete(penempatan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}