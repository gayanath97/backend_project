package com.ems.repository;

import com.ems.entity.OpdBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdBillRepository extends JpaRepository<OpdBill,Long> {
}
