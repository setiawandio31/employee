package com.example.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employee.model.dto.KategoriKemampuanDTO;
import com.example.employee.model.dto.ListKemampuanDTO;
import com.example.employee.model.entity.ListKemampuan;
import com.example.employee.repository.ListKemampuanRepository;

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
@RequestMapping("/api/listKemampuan")
public class ListKemampuanController {
    @Autowired
    ListKemampuanRepository listKemampuanRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/readAll")
    public Map<String, Object> readAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<ListKemampuan> listListKemampuanEntity = listKemampuanRepository.findAll();
        List<ListKemampuanDTO> listListKemampuanDTO = new ArrayList<ListKemampuanDTO>();

        for ( ListKemampuan listKemampuan : listListKemampuanEntity ) {
            ListKemampuanDTO listKemampuanDTO = modelMapper.map(listKemampuan, ListKemampuanDTO.class);
            listListKemampuanDTO.add(listKemampuanDTO);
        }

        result.put("status", 200);
        result.put("message", "Read All data success");
        result.put("data", listListKemampuanDTO);

        return result;
    }

    @GetMapping("/read/{id}")
    public Map<String, Object> readById(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        ListKemampuan listKemampuan = listKemampuanRepository.findById(id).orElseThrow();
        ListKemampuanDTO listKemampuanDTO = modelMapper.map(listKemampuan, ListKemampuanDTO.class);

        result.put("status", 200);
        result.put("message", "Read By Id success");
        result.put("data", listKemampuanDTO);

        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody KategoriKemampuanDTO listKemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        ListKemampuan listKemampuan = modelMapper.map(listKemampuanDTO, ListKemampuan.class);
        
        listKemampuanRepository.save(listKemampuan);

        result.put("status", 200);
        result.put("message", "Create data success");
        result.put("data", listKemampuanDTO);
        
        return result;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody KategoriKemampuanDTO listKemampuanDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        ListKemampuan listKemampuan = modelMapper.map(listKemampuanDTO, ListKemampuan.class);
        listKemampuan.setIdListKemampuan(id);

        listKemampuanRepository.save(listKemampuan);

        result.put("status", 200);
        result.put("message", "Update Success");
        result.put("data", listKemampuanDTO);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        ListKemampuan listKemampuan = listKemampuanRepository.findById(id).orElseThrow();
        
        listKemampuanRepository.delete(listKemampuan);

        result.put("status", 200);
        result.put("message", "Delete Success");

        return result;
    }
}