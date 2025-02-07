package com.example.bootcamp.service;

import com.example.bootcamp.entity.Center;
import com.example.bootcamp.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }
}