package com.ems.repository;

import com.ems.entity.EmployeeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeExpenseRepository extends JpaRepository<EmployeeExpense,Long> {


}
