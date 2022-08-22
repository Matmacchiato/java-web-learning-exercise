package com.example.springbootjpamysqlentitymapping.repository;

import com.example.springbootjpamysqlentitymapping.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby,Long> {
}
