package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.AgamaDTO;
import com.example.employee.model.entity.Agama;
import com.example.employee.repository.AgamaRepository;

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
@RequestMapping("/api/agama")
public class AgamaController {
    @Autowired
    AgamaRepository agamaRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping()
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Agama> listAgamaEntity = agamaRepository.findAll();
        List<AgamaDTO> listAgamaDTO = new ArrayList<AgamaDTO>();

        for ( Agama agama : listAgamaEntity ) {
            AgamaDTO agamaDTO = modelMapper.map(agama, AgamaDTO.class);
            listAgamaDTO.add(agamaDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listAgamaDTO);

        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Agama agama = agamaRepository.findById(id).orElseThrow();
        AgamaDTO agamaDTO = modelMapper.map(agama, AgamaDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", agamaDTO);

        return result;
    }

    @PostMapping()
    public Map<String, Object> create(@Valid @RequestBody AgamaDTO agamaDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Agama agama = modelMapper.map(agamaDTO, Agama.class);
        
        agamaRepository.save(agama);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", agamaDTO);
        
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AgamaDTO agamaDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        Agama agama = modelMapper.map(agamaDTO, Agama.class);
        agama.setIdAgama(id);

        agamaRepository.save(agama);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", agamaDTO);

        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Agama agama = agamaRepository.findById(id).orElseThrow();
        
        agamaRepository.delete(agama);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}