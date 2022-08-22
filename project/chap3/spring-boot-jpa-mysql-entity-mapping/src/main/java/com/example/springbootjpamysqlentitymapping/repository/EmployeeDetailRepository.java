package com.example.springbootjpamysqlentitymapping.repository;

import com.example.springbootjpamysqlentitymapping.model.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail,Long> {
}
