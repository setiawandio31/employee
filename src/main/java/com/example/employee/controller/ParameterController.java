package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.ParameterDTO;
import com.example.employee.model.entity.Parameter;
import com.example.employee.repository.ParameterRepository;

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
@RequestMapping("/api/parameter")
public class ParameterController {
    @Autowired
    ParameterRepository parameterRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Parameter> listParameterEntity = parameterRepository.findAll();
        List<ParameterDTO> listParameterDTO = new ArrayList<ParameterDTO>();

        for ( Parameter parameter : listParameterEntity ) {
            ParameterDTO parameterDTO = modelMapper.map(parameter, ParameterDTO.class);
            listParameterDTO.add(parameterDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listParameterDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Parameter parameter = parameterRepository.findById(id).orElseThrow();
        ParameterDTO parameterDTO = modelMapper.map(parameter, ParameterDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", parameterDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody ParameterDTO parameterDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Parameter parameter = modelMapper.map(parameterDTO, Parameter.class);
        
        parameterRepository.save(parameter);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", parameterDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParameterDTO parameterDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Parameter parameter = modelMapper.map(parameterDTO, Parameter.class);
        parameter.setIdParam(id);

        parameterRepository.save(parameter);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", parameterDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Parameter parameter = parameterRepository.findById(id).orElseThrow();
        
        parameterRepository.delete(parameter);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}