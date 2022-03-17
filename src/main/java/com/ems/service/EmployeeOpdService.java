package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.EmployeeExpenseDTO;
import com.ems.dto.EmployeeOpdDTO;
import com.ems.entity.EmployeeExpense;
import com.ems.entity.EmployeeOpd;
import com.ems.repository.EmployeeOpdRepository;
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
 * This class is responsible handle all logic's in EmployeeOpdService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.02.01
 */
@Service
public class EmployeeOpdService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeOpdService.class);

    private EmployeeOpdRepository employeeOpdRepository;
    private ModelMapper modelMapper;
    private OpdService opdService;
    private EmployeeService employeeService;
    private ExpenseService expenseService;

    @Autowired
    @Lazy
    public EmployeeOpdService(EmployeeOpdRepository employeeOpdRepository, ModelMapper modelMapper,OpdService opdService,EmployeeService employeeService,ExpenseService expenseService) {
        this.employeeOpdRepository = employeeOpdRepository;
        this.modelMapper = modelMapper;
        this.opdService = opdService;
        this.employeeService = employeeService;
        this.expenseService = expenseService;

    }


    /**
     * =================================================================
     * This method is responsible save {@link EmployeeOpd}.
     * =================================================================
     *
     * @param employeeOpdDTO
     * @return
     */
    public CommonResponse saveEmployeeOpd(EmployeeOpdDTO employeeOpdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeOpd employeeOpd = new EmployeeOpd();

            employeeOpd.setId(Long.valueOf(employeeOpdDTO.getId()));
            employeeOpd.setEmployee(employeeService.findById(employeeOpdDTO.getId()));
            employeeOpd.setOpd(opdService.findById(employeeOpdDTO.getOpd()));

            employeeOpdRepository.save(employeeOpd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeOpdService -> saveEmployeeOpd()" + e);
        }
        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible update {@link EmployeeOpd}.
     * =================================================================
     *
     * @param id
     * @param employeeOpdDTO
     * @return
     */
    public CommonResponse updateEmployeeOpd(String id, EmployeeOpdDTO employeeOpdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeOpd employeeOpd = employeeOpdRepository.getById(Long.valueOf(id));

            employeeOpd.setId(Long.valueOf(employeeOpdDTO.getId()));
            employeeOpd.setEmployee(employeeService.findById(employeeOpdDTO.getId()));
            employeeOpd.setOpd(opdService.findById(employeeOpdDTO.getOpd()));


            employeeOpdRepository.save(employeeOpd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeOpdService -> updateEmployeeOpd()" + e);
        }
        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible delete {@link EmployeeOpd}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse deleteEmployeeOpd(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeOpd employeeOpd = employeeOpdRepository.getById(Long.valueOf(id));

            employeeOpd.setStatuss(CommonStatus.DELETE);
            employeeOpdRepository.save(employeeOpd);
            commonResponse.setStatus(true);


        }catch (Exception e){

            LOGGER.error("/**************** Exception in EmployeeOpdService -> deleteEmployeeOpd()" + e);

        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible get all {@link EmployeeOpd}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<EmployeeOpdDTO> employeeOpdDTOS = null;

        try {

            List<EmployeeOpd> employeeOpds = employeeOpdRepository.findAll();

            employeeOpdDTOS = castToDTOS(employeeOpds);

            commonResponse.setPayload(Collections.singletonList(employeeOpdDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeOpdService -> getAll()" + e);
        }
        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible cast to dtos {@link EmployeeOpd}.
     * =================================================================
     *
     * @param employeeOpds
     * @return
     */
    private List<EmployeeOpdDTO> castToDTOS(List<EmployeeOpd> employeeOpds) {

        List<EmployeeOpdDTO> employeeOpdDTOS = new ArrayList<>();

        for(EmployeeOpd employeeOpd : employeeOpds){

            EmployeeOpdDTO employeeOpdDTO = modelMapper.map(employeeOpd,EmployeeOpdDTO.class);
            employeeOpdDTOS.add(employeeOpdDTO);


        }

        return employeeOpdDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast to employeeOpds {@link EmployeeOpd}.
     * =================================================================
     *
     * @param employeeOpdDTOs
     * @return
     */
    public Set<EmployeeOpd> castToEmployeeOpds(Set<EmployeeOpdDTO> employeeOpdDTOs) {

        Set<EmployeeOpd> employeeOpds = new HashSet<>();

        for(EmployeeOpdDTO employeeOpdDTO : employeeOpdDTOs){

            EmployeeOpd employeeOpd = modelMapper.map(employeeOpdDTO,EmployeeOpd.class);
            employeeOpds.add(employeeOpd);


        }

        return employeeOpds;
    }

    /**
     * =================================================================
     * This method is responsible get bY id {@link EmployeeOpd}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        EmployeeOpdDTO employeeOpdDTO = null;


        try {

            EmployeeOpd employeeOpd = employeeOpdRepository.findById(Long.valueOf(id)).get();
            employeeOpdDTO = modelMapper.map(employeeOpd,EmployeeOpdDTO.class);

            commonResponse.setPayload(Collections.singletonList(employeeOpdDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeOpdService -> getById()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible find bY id {@link EmployeeOpd}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public  EmployeeOpd findById(String id){
        return employeeOpdRepository.findById(Long.valueOf(id)).get();
    }
}
