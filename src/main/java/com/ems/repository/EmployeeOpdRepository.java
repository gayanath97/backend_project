package com.ems.repository;

import com.ems.entity.EmployeeOpd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOpdRepository extends JpaRepository<EmployeeOpd,Long> {

}
