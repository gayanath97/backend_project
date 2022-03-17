package com.ems.repository;

import com.ems.entity.RrBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrBillRepository extends JpaRepository<RrBill,Long> {
}
