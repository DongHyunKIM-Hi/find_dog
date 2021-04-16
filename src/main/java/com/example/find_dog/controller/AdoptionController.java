package com.example.find_dog.controller;


import com.example.find_dog.model.dto.AdoptionDto;
import com.example.find_dog.model.entity.Adoption;
import com.example.find_dog.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdoptionController {
    private final AdoptionService adoptionService;

    @GetMapping("api/Adoption")
    public List<Adoption> showAll(){
        return adoptionService.showAll_adoption();
    }

    @GetMapping("api/Adoption/type")
    public List<Adoption> showByType(@RequestBody AdoptionDto adoptionDto){
        return adoptionService.showByType(adoptionDto.getType());
    }
}
