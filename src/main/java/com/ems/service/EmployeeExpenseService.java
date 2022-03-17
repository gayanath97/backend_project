package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.EmployeeExpenseDTO;
import com.ems.entity.Employee;
import com.ems.entity.EmployeeExpense;
import com.ems.repository.EmployeeExpenseRepository;
import com.ems.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * ========================================================================
 * This class is responsible handle all logic's in EmployeeExpenseService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.02.01
 */
@Service
public class EmployeeExpenseService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeExpenseService.class);

    private EmployeeExpenseRepository employeeExpenseRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private ExpenseService expenseService;

    @Autowired
    @Lazy
    public EmployeeExpenseService(EmployeeExpenseRepository employeeExpenseRepository, ModelMapper modelMapper,EmployeeService employeeService,ExpenseService expenseService) {
        this.employeeExpenseRepository = employeeExpenseRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.expenseService = expenseService;
    }


    /**
     * =================================================================
     * This method is responsible save {@link EmployeeExpense}.
     * =================================================================
     *
     * @param employeeExpenseDTO
     * @return
     */
    public CommonResponse saveEmployeeExpense(EmployeeExpenseDTO employeeExpenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeExpense employeeExpense = new EmployeeExpense();

            employeeExpense.setId(Long.valueOf(employeeExpenseDTO.getId()));
            employeeExpense.setEmployee(employeeService.findById(employeeExpenseDTO.getId()));
            employeeExpense.setExpense(expenseService.findById(employeeExpenseDTO.getExpense()));

            employeeExpenseRepository.save(employeeExpense);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeExpenseService -> saveEmployeeExpense()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link EmployeeExpense}.
     * =================================================================
     *
     * @param id
     * @param employeeExpenseDTO
     * @return
     */
    public CommonResponse updateEmployeeExpense(String id, EmployeeExpenseDTO employeeExpenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeExpense employeeExpense = employeeExpenseRepository.getById(Long.valueOf(id));

            employeeExpense.setId(Long.valueOf(employeeExpenseDTO.getId()));
            employeeExpense.setEmployee(employeeService.findById(employeeExpenseDTO.getId()));
            employeeExpense.setExpense(expenseService.findById(employeeExpenseDTO.getExpense()));

            employeeExpenseRepository.save(employeeExpense);
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeExpenseService -> updateEmployeeExpense()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link EmployeeExpense}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse deleteEmployeeExpense(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeExpense employeeExpense = employeeExpenseRepository.getById(Long.valueOf(id));

            employeeExpense.setStatuss(CommonStatus.DELETE);
            employeeExpenseRepository.save(employeeExpense);
            commonResponse.setStatus(true);


        }catch (Exception e){

            LOGGER.error("/**************** Exception in EmployeeExpenseService -> deleteEmployeeExpense()" + e);

        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible get all {@link EmployeeExpense}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<EmployeeExpenseDTO> employeeExpenseDTOS = null;

        try {

           List<EmployeeExpense> employeeExpenses = employeeExpenseRepository.findAll();

           employeeExpenseDTOS = castToDTOS(employeeExpenses);

           commonResponse.setPayload(Collections.singletonList(employeeExpenseDTOS));
           commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeExpenseService -> getAll()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast to dtos {@link EmployeeExpense}.
     * =================================================================
     *
     * @param employeeExpenses
     * @return
     */
    private List<EmployeeExpenseDTO> castToDTOS(List<EmployeeExpense> employeeExpenses) {

        List<EmployeeExpenseDTO> employeeExpenseDTOS = new ArrayList<>();

        for(EmployeeExpense employeeExpense : employeeExpenses){

            EmployeeExpenseDTO employeeExpenseDTO = modelMapper.map(employeeExpense,EmployeeExpenseDTO.class);
            employeeExpenseDTOS.add(employeeExpenseDTO);


        }

        return employeeExpenseDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast to employeeExpenses {@link EmployeeExpense}.
     * =================================================================
     *
     * @param employeeExpenseDTOs
     * @return
     */
    public Set<EmployeeExpense> castToEmployeeExpenses(Set<EmployeeExpenseDTO> employeeExpenseDTOs) {

        Set<EmployeeExpense> employeeExpenses = new HashSet<>();

        for(EmployeeExpenseDTO employeeExpenseDTO : employeeExpenseDTOs){

            EmployeeExpense employeeExpense = modelMapper.map(employeeExpenseDTO,EmployeeExpense.class);
            employeeExpenses.add(employeeExpense);


        }

        return employeeExpenses;
    }

    /**
     * =================================================================
     * This method is responsible get bY id {@link EmployeeExpense}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        EmployeeExpenseDTO employeeExpenseDTO = null;


        try {

            EmployeeExpense employeeExpense = employeeExpenseRepository.findById(Long.valueOf(id)).get();
            employeeExpenseDTO = modelMapper.map(employeeExpense,EmployeeExpenseDTO.class);

            commonResponse.setPayload(Collections.singletonList(employeeExpenseDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeExpenseService -> getById()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible find bY id {@link EmployeeExpense}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public  EmployeeExpense findById(String id){
        return employeeExpenseRepository.findById(Long.valueOf(id)).get();
    }

}
