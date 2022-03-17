package com.ems.service;

import com.ems.dto.ExpenseDTO;
import com.ems.entity.Expense;
import com.ems.repository.ExpenseRepository;
import com.ems.util.CommonResponse;
import com.ems.constant.CommonStatus;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in ExpenseService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class ExpenseService {

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseService.class);

    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private ExpenseBillService expenseBillService;
    private EmployeeExpenseService employeeExpenseService;


    @Autowired
    @Lazy
    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper,EmployeeService employeeService,ExpenseBillService expenseBillService,EmployeeExpenseService employeeExpenseService) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.employeeService=employeeService;
        this.expenseBillService = expenseBillService;
        this.employeeExpenseService = employeeExpenseService;
    }


    /**
     * =================================================================
     * This method is responsible save {@link Expense}.
     * =================================================================
     *
     * @param expenseDTO
     * @return CommonResponse
     */
    public CommonResponse saveExpense(ExpenseDTO expenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = new Expense();

          //  expense.setId(Long.valueOf(expenseDTO.getId()));
            expense.setBuOrDept(expenseDTO.getBuOrDept());
            expense.setProject(expenseDTO.getProject());
            expense.setExtensionNo(Integer.valueOf(expenseDTO.getExtensionNo()));
            expense.setCustomer(expenseDTO.getCustomer());
            expense.setLocation(expenseDTO.getLocation());
            expense.setBillability(expenseDTO.getBillability());

            expense.setSta_tus("pending");

          //  expense.setEmployeeExpenses(employeeExpenseService.castToEmployeeExpenses(expenseDTO.getEmployeeExpenses()));
           // expense.setExpenseBills(expenseBillService.castExpenseBillDTOSIntoExpenseBills(expenseDTO.getExpenseBills()));

              expenseRepository.save(expense);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseService -> saveExpense()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link Expense}.
     * =================================================================
     *
     * @param id
     * @param expenseDTO
     * @return CommonResponse
     */
    public CommonResponse updateExpense(String id, ExpenseDTO expenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = expenseRepository.getById(Long.valueOf(id));

          //  expense.setId(Long.valueOf(expenseDTO.getId()));
            expense.setBuOrDept(expenseDTO.getBuOrDept());
            expense.setProject(expenseDTO.getProject());
            expense.setExtensionNo(Integer.valueOf(expenseDTO.getExtensionNo()));
            expense.setCustomer(expenseDTO.getCustomer());
            expense.setLocation(expenseDTO.getLocation());
            expense.setBillability(expenseDTO.getBillability());

            expense.setSta_tus("pending");


        //    expense.setEmployeeExpenses(employeeExpenseService.castToEmployeeExpenses(expenseDTO.getEmployeeExpenses()));
        //    expense.setExpenseBills(expenseBillService.castExpenseBillDTOSIntoExpenseBills(expenseDTO.getExpenseBills()));

            expenseRepository.save(expense);
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseService -> updateExpense()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link Expense}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteExpense(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = expenseRepository.getById(Long.valueOf(id));

            //.setStatuss(CommonStatus.DELETE);
          //  expenseRepository.save(expense);

            expenseRepository.delete(expense);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseService -> deleteExpense()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible getAll {@link Expense}.
     * =================================================================
     *
     * @return CommonResponse
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<ExpenseDTO> expenseDTOS = null;

        try {

            List<Expense> expenses = expenseRepository.findAll();
            expenseDTOS = castExpensesIntoExpenseDTOS(expenses);
            commonResponse.setPayload(Collections.singletonList(expenseDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseService -> getAll()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast expenses to expensedtos {@link Expense}.
     * =================================================================
     *
     * @param expenses
     * @return List
     */
    private List<ExpenseDTO> castExpensesIntoExpenseDTOS(List<Expense> expenses) {

        List<ExpenseDTO> expenseDTOS = new ArrayList<>();

        for (Expense expense : expenses){
            ExpenseDTO expenseDTO = modelMapper.map(expense,ExpenseDTO.class);
            expenseDTOS.add(expenseDTO);
        }

        return expenseDTOS;

    }

    /**
     * =================================================================
     * This method is responsible cast expensedtos to expenses {@link Expense}.
     * =================================================================
     *
     * @param expenseDTOS
     * @return Set
     */
    public Set<Expense> castExpenseDTOSIntoExpenses(Set<ExpenseDTO> expenseDTOS) {

        Set<Expense> expenses = new HashSet<>();

        for (ExpenseDTO expenseDTO : expenseDTOS){
            Expense expense = modelMapper.map(expenseDTO,Expense.class);
            expenses.add(expense);
        }

        return expenses;

    }

    /**
     * =================================================================
     * This method is responsible to get by id {@link Expense}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        ExpenseDTO expenseDTO = null;

        try {

            Expense expense = expenseRepository.findById(Long.valueOf(id)).get();
            expenseDTO = modelMapper.map(expense,ExpenseDTO.class);
            commonResponse.setPayload(Collections.singletonList(expenseDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseService -> getById()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible find by id {@link Expense}.
     * =================================================================
     *
     * @param id
     * @return Expense
     */
    public Expense findById(String id){

        Expense expense = expenseRepository.findById(Long.valueOf(id)).get();

        return expense;
    }


}
