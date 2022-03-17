package com.ems.repository;

import com.ems.entity.ExpenseBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseBillRepository extends JpaRepository<ExpenseBill,Long> {
}
