package com.example.find_dog.repository;


import com.example.find_dog.model.entity.Mbti_type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mbti_typeRepository extends JpaRepository<Mbti_type,Long> {
    Mbti_type findByType(String want);
}