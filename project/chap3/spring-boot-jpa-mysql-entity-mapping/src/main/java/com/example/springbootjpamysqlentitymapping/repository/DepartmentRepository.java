package com.example.springbootjpamysqlentitymapping.repository;

import com.example.springbootjpamysqlentitymapping.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
