package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.ParameterPajakDTO;
import com.example.employee.model.entity.ParameterPajak;
import com.example.employee.repository.ParameterPajakRepository;

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
@RequestMapping("/api/parameterPajak")
public class ParameterPajakController {
    @Autowired
    ParameterPajakRepository parameterPajakRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<ParameterPajak> listParameterPajakEntity = parameterPajakRepository.findAll();
        List<ParameterPajakDTO> listParameterPajakDTO = new ArrayList<ParameterPajakDTO>();

        for ( ParameterPajak parameterPajak : listParameterPajakEntity ) {
            ParameterPajakDTO parameterPajakDTO = modelMapper.map(parameterPajak, ParameterPajakDTO.class);
            listParameterPajakDTO.add(parameterPajakDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listParameterPajakDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        ParameterPajak parameterPajak = parameterPajakRepository.findById(id).orElseThrow();
        ParameterPajakDTO parameterPajakDTO = modelMapper.map(parameterPajak, ParameterPajakDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", parameterPajakDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody ParameterPajakDTO parameterPajakDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        ParameterPajak parameterPajak = modelMapper.map(parameterPajakDTO, ParameterPajak.class);
        
        parameterPajakRepository.save(parameterPajak);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", parameterPajakDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParameterPajakDTO parameterPajakDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        ParameterPajak parameterPajak = modelMapper.map(parameterPajakDTO, ParameterPajak.class);
        parameterPajak.setIdParamPajak(id);

        parameterPajakRepository.save(parameterPajak);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", parameterPajakDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        ParameterPajak parameterPajak = parameterPajakRepository.findById(id).orElseThrow();
        
        parameterPajakRepository.delete(parameterPajak);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}