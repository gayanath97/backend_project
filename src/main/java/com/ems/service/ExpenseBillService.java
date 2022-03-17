package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.ExpenseBillDTO;
import com.ems.entity.Employee;
import com.ems.entity.ExpenseBill;
import com.ems.repository.ExpenseBillRepository;
import com.ems.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in ExpenseBillService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class ExpenseBillService {

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseBillService.class);

    private ExpenseBillRepository expenseBillRepository;
    private ModelMapper modelMapper;
    private ExpenseService expenseService;

    @Autowired
    public ExpenseBillService(ExpenseBillRepository expenseBillRepository, ModelMapper modelMapper,ExpenseService expenseService) {
        this.expenseBillRepository = expenseBillRepository;
        this.modelMapper = modelMapper;
        this.expenseService = expenseService;
    }

    /**
     * =================================================================
     * This method is responsible save {@link ExpenseBill}.
     * =================================================================
     *
     * @param expenseBillDTO
     * @return CommonResponse
     */
    public CommonResponse saveExpenseBill(ExpenseBillDTO expenseBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = new ExpenseBill();

         //   expenseBill.setId(Long.valueOf(expenseBillDTO.getId()));

            expenseBill.setExtensionNo(Integer.valueOf(expenseBillDTO.getExtensionNo()));

            expenseBill.setParticulars(expenseBillDTO.getParticulars());
            expenseBill.setAmount(Double.valueOf(expenseBillDTO.getAmount()));
            expenseBill.setSta_tus("pending");

            expenseBill.setExpense(expenseService.findById(expenseBillDTO.getExpense()));

            expenseBillRepository.save(expenseBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseBillService -> saveExpenseBill()" + e);
        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link ExpenseBill}.
     * =================================================================
     *
     * @param id
     * @param expenseBillDTO
     * @return CommonResponse
     */
    public CommonResponse updateExpenseBill(String id, ExpenseBillDTO expenseBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = expenseBillRepository.getById(Long.valueOf(id));

      //      expenseBill.setId(Long.valueOf(expenseBillDTO.getId()));

            expenseBill.setExtensionNo(Integer.valueOf(expenseBillDTO.getExtensionNo()));
            expenseBill.setParticulars(expenseBillDTO.getParticulars());
            expenseBill.setAmount(Double.valueOf(expenseBillDTO.getAmount()));

            expenseBill.setSta_tus("pending");

            expenseBill.setExpense(expenseService.findById(expenseBillDTO.getExpense()));

            expenseBillRepository.save(expenseBill);
            commonResponse.setStatus(true);



        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseBillService -> updateExpenseBill()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible delete {@link ExpenseBill}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteExpenseBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = expenseBillRepository.getById(Long.valueOf(id));

          //  expenseBill.setStatuss(CommonStatus.DELETE);
       //     expenseBillRepository.save(expenseBill);

            expenseBillRepository.delete(expenseBill);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseBillService -> deleteExpenseBill()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible for getting all {@link ExpenseBill}.
     * =================================================================
     *
     * @return CommonResponse
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<ExpenseBillDTO> expenseBillDTOS = null;

        try {
            List<ExpenseBill> expenseBills = expenseBillRepository.findAll();

            expenseBillDTOS = castExpenseBillsIntoExpenseBillDTOS(expenseBills);

            commonResponse.setPayload(Collections.singletonList(expenseBillDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseBillService -> getAll()" + e);
        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible for cast bills to bilDTOS {@link ExpenseBill}.
     * =================================================================
     *
     * @param expenseBills
     * @return List<ExpenseBillDTO>
     */
    private List<ExpenseBillDTO> castExpenseBillsIntoExpenseBillDTOS(List<ExpenseBill> expenseBills) {

        List<ExpenseBillDTO > expenseBillDTOS = new ArrayList<>();

        for(ExpenseBill expenseBill : expenseBills){
           // ExpenseBillDTO expenseBillDTO = modelMapper.map(expenseBill,ExpenseBillDTO.class);

            ExpenseBillDTO expenseBillDTO =new ExpenseBillDTO();

            expenseBillDTO.setId(String.valueOf(expenseBill.getId()));
            expenseBillDTO.setExtensionNo(String.valueOf(expenseBill.getExtensionNo()));
            expenseBillDTO.setAmount(String.valueOf(expenseBill.getAmount()));
            expenseBillDTO.setParticulars(expenseBill.getParticulars());
            expenseBillDTO.setExpense(String.valueOf(expenseBill.getExpense().getId()));
            expenseBillDTO.setSta_tus("pending");

            expenseBillDTOS.add(expenseBillDTO);
        }
      return expenseBillDTOS;
    }

    /**
     * =================================================================
     * This method is responsible to cast billdtos to bills {@link ExpenseBill}.
     * =================================================================
     *
     * @param expenseBillDTOS
     * @return set
     */
    public Set<ExpenseBill> castExpenseBillDTOSIntoExpenseBills(Set<ExpenseBillDTO> expenseBillDTOS) {

        Set<ExpenseBill > expenseBills = new HashSet<>();

        for(ExpenseBillDTO expenseBillDTO : expenseBillDTOS){
            ExpenseBill expenseBill = modelMapper.map(expenseBillDTO,ExpenseBill.class);
            expenseBills.add(expenseBill);
        }
        return expenseBills;
    }

    /**
     * =================================================================
     * This method is responsible to get by id {@link ExpenseBill}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
    //    ExpenseBillDTO expenseBillDTO = null;

        try {

            ExpenseBill expenseBill = expenseBillRepository.findById(Long.valueOf(id)).get();

//            expenseBillDTO = modelMapper.map(expenseBill,ExpenseBillDTO.class);

            ExpenseBillDTO expenseBillDTO = new ExpenseBillDTO();

            expenseBillDTO.setId(String.valueOf(expenseBill.getId()));
            expenseBillDTO.setExtensionNo(String.valueOf(expenseBill.getExtensionNo()));
            expenseBillDTO.setAmount(String.valueOf(expenseBill.getAmount()));
            expenseBillDTO.setParticulars(expenseBill.getParticulars());
            expenseBillDTO.setExpense(String.valueOf(expenseBill.getExpense().getId()));
            expenseBillDTO.setSta_tus("pending");

            commonResponse.setPayload(Collections.singletonList(expenseBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in ExpenseBillService -> getById()" + e);
        }

       return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to find by id {@link Employee}.
     * =================================================================
     *
     * @param id
     * @return ExpenseBill
     */
    public ExpenseBill findById(String id){
        return expenseBillRepository.findById(Long.valueOf(id)).get();
    }


}
