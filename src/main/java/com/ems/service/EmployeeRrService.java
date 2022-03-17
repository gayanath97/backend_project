package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.EmployeeExpenseDTO;
import com.ems.dto.EmployeeRrDTO;
import com.ems.entity.EmployeeExpense;
import com.ems.entity.EmployeeRr;
import com.ems.repository.EmployeeRrRepository;
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
 * This class is responsible handle all logic's in EmployeeRrService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.02.02
 */
@Service
public class EmployeeRrService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeRrService.class);

    private EmployeeRrRepository employeeRrRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private RrService rrService;

    @Autowired
    @Lazy
    public EmployeeRrService(EmployeeRrRepository employeeRrRepository, ModelMapper modelMapper,EmployeeService employeeService,RrService rrService) {
        this.employeeRrRepository = employeeRrRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.rrService = rrService;
    }


    /**
     * =================================================================
     * This method is responsible save {@link EmployeeRr}.
     * =================================================================
     *
     * @param employeeRrDTO
     * @return
     */
    public CommonResponse saveEmployeeRr(EmployeeRrDTO employeeRrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeRr employeeRr = new EmployeeRr();

            employeeRr.setId(Long.valueOf(employeeRrDTO.getId()));
            employeeRr.setEmployee(employeeService.findById(employeeRrDTO.getId()));
            employeeRr.setRr(rrService.findById(employeeRrDTO.getRr()));

            employeeRrRepository.save(employeeRr);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeRrService -> saveEmployeeRr()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link EmployeeRr}.
     * =================================================================
     *
     * @param id
     * @param employeeRrDTO
     * @return
     */
    public CommonResponse updateEmployeeRr(String id, EmployeeRrDTO employeeRrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeRr employeeRr = employeeRrRepository.getById(Long.valueOf(id));

            employeeRr.setId(Long.valueOf(employeeRrDTO.getId()));
            employeeRr.setEmployee(employeeService.findById(employeeRrDTO.getId()));
            employeeRr.setRr(rrService.findById(employeeRrDTO.getRr()));

            employeeRrRepository.save(employeeRr);
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeRrService -> updateEmployeeRr()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link EmployeeRr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse deleteEmployeeRr(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            EmployeeRr employeeRr = employeeRrRepository.getById(Long.valueOf(id));

            employeeRr.setStatuss(CommonStatus.DELETE);
            employeeRrRepository.save(employeeRr);
            commonResponse.setStatus(true);


        }catch (Exception e){

            LOGGER.error("/**************** Exception in EmployeeRrService -> deleteEmployeeRr()" + e);

        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible get all {@link EmployeeRr}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<EmployeeRrDTO> employeeRrDTOS = null;

        try {

            List<EmployeeRr> employeeRrs = employeeRrRepository.findAll();

            employeeRrDTOS = castToDTOS(employeeRrs);

            commonResponse.setPayload(Collections.singletonList(employeeRrDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeRrService -> getAll()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast to dtos {@link EmployeeRr}.
     * =================================================================
     *
     * @param employeeRrs
     * @return
     */
    private List<EmployeeRrDTO> castToDTOS(List<EmployeeRr> employeeRrs) {

        List<EmployeeRrDTO> employeeRrDTOS = new ArrayList<>();

        for(EmployeeRr employeeRr : employeeRrs){

            EmployeeRrDTO employeeRrDTO = modelMapper.map(employeeRr,EmployeeRrDTO.class);
            employeeRrDTOS.add(employeeRrDTO);


        }

        return employeeRrDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast to employeeRrs {@link EmployeeRr}.
     * =================================================================
     *
     * @param employeeRrDTOs
     * @return
     */
    public Set<EmployeeRr> castToEmployeeRrs(Set<EmployeeRrDTO> employeeRrDTOs) {

        Set<EmployeeRr> employeeRrs = new HashSet<>();

        for(EmployeeRrDTO employeeRrDTO : employeeRrDTOs){

            EmployeeRr employeeRr = modelMapper.map(employeeRrDTO,EmployeeRr.class);
            employeeRrs.add(employeeRr);


        }

        return employeeRrs;
    }

    /**
     * =================================================================
     * This method is responsible get bY id {@link EmployeeRr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        EmployeeRrDTO employeeRrDTO = null;


        try {

            EmployeeRr employeeRr = employeeRrRepository.findById(Long.valueOf(id)).get();
            employeeRrDTO = modelMapper.map(employeeRr,EmployeeRrDTO.class);

            commonResponse.setPayload(Collections.singletonList(employeeRrDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeRrService -> getById()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible find bY id {@link EmployeeRr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public  EmployeeRr findById(String id){
        return employeeRrRepository.findById(Long.valueOf(id)).get();
    }
}
