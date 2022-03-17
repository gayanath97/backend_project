package com.ems.repository;

import com.ems.entity.OpdAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdAmountRepository extends JpaRepository<OpdAmount,Long> {
}
