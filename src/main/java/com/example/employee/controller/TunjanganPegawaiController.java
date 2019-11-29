package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.TunjanganPegawaiDTO;
import com.example.employee.model.entity.TunjanganPegawai;
import com.example.employee.repository.TunjanganPegawaiRepository;

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
@RequestMapping("/api/tunjanganPegawai")
public class TunjanganPegawaiController {
    @Autowired
    TunjanganPegawaiRepository tunjanganPegawaiRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TunjanganPegawai> listTunjanganPegawaiEntity = tunjanganPegawaiRepository.findAll();
        List<TunjanganPegawaiDTO> listTunjanganPegawaiDTO = new ArrayList<TunjanganPegawaiDTO>();

        for ( TunjanganPegawai tunjanganPegawai : listTunjanganPegawaiEntity ) {
            TunjanganPegawaiDTO tunjanganPegawaiDTO = modelMapper.map(tunjanganPegawai, TunjanganPegawaiDTO.class);
            listTunjanganPegawaiDTO.add(tunjanganPegawaiDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listTunjanganPegawaiDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        TunjanganPegawai tunjanganPegawai = tunjanganPegawaiRepository.findById(id).orElseThrow();
        TunjanganPegawaiDTO tunjanganPegawaiDTO = modelMapper.map(tunjanganPegawai, TunjanganPegawaiDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", tunjanganPegawaiDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody TunjanganPegawaiDTO tunjanganPegawaiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        TunjanganPegawai tunjanganPegawai = modelMapper.map(tunjanganPegawaiDTO, TunjanganPegawai.class);
        
        tunjanganPegawaiRepository.save(tunjanganPegawai);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", tunjanganPegawaiDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TunjanganPegawaiDTO tunjanganPegawaiDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        TunjanganPegawai tunjanganPegawai = modelMapper.map(tunjanganPegawaiDTO, TunjanganPegawai.class);
        tunjanganPegawai.setIdTunjanganPegawai(id);

        tunjanganPegawaiRepository.save(tunjanganPegawai);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", tunjanganPegawaiDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        TunjanganPegawai tunjanganPegawai = tunjanganPegawaiRepository.findById(id).orElseThrow();
        
        tunjanganPegawaiRepository.delete(tunjanganPegawai);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}