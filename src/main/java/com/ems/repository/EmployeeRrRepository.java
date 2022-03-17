package com.ems.repository;

import com.ems.entity.EmployeeRr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRrRepository extends JpaRepository<EmployeeRr,Long> {



}
