package com.example.springbootjpamysqlentitymapping.repository;

import com.example.springbootjpamysqlentitymapping.model.DepartmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDetailRepository extends JpaRepository<DepartmentDetail,Long> {
}
