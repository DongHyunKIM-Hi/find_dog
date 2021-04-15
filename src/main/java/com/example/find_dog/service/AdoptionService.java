package com.example.find_dog.service;

import com.example.find_dog.model.entity.Adoption;
import com.example.find_dog.repository.AdoptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;

    public List<Adoption> showAll_adoption(){
        return adoptionRepository.findAll();
    }

    public List<Adoption> showByType(String type){
        return adoptionRepository.findByType(type);
    }
}
