package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

}
