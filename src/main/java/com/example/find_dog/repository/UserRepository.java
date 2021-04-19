package com.example.find_dog.repository;

import com.example.find_dog.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 쿼리가 수행될 때 Lazy 조회가 아니고 Eager 조회로 authorities 정보를 같이 가져오게 됨
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}