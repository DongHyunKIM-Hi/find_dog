package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Adoption;
import com.example.find_dog.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findAll();
    List<Adoption> findByType(String type);
}
