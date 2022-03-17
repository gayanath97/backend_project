package com.ems.repository;

import com.ems.entity.Opd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdRepository extends JpaRepository<Opd,Long> {
}
