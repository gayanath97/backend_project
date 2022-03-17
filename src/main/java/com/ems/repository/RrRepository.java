package com.ems.repository;

import com.ems.entity.Rr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrRepository extends JpaRepository<Rr,Long> {


}
