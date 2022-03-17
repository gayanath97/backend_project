package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.OpdAmountDTO;
import com.ems.entity.OpdAmount;
import com.ems.repository.OpdAmountRepository;
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
 * This class is responsible handle all logic's in OpdAmountService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class OpdAmountService {

    private final Logger LOGGER = LoggerFactory.getLogger(OpdAmountService.class);

    private OpdAmountRepository opdAmountRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private EmployeeService employeeService;


    @Autowired
    @Lazy
    public OpdAmountService(OpdAmountRepository opdAmountRepository, ModelMapper modelMapper,RewardService rewardService,EmployeeService employeeService) {
        this.opdAmountRepository = opdAmountRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.employeeService=employeeService;
    }


    /**
     * =================================================================
     * This method is responsible save {@link OpdAmount}.
     * =================================================================
     *
     * @param opdAmountDTO
     * @return CommonResponse
     */
    public CommonResponse saveOpdAmount(OpdAmountDTO opdAmountDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = new OpdAmount();
           // EmployeeDTO employeeDTO = new EmployeeDTO();

            opdAmount.setId(Long.valueOf(opdAmountDTO.getId()));
            opdAmount.setAmount(Double.valueOf(opdAmountDTO.getAmount()));
            opdAmount.setExpireDate(rewardService.stringToDate(opdAmountDTO.getExpireDate()));
            opdAmount.setEmployee(employeeService.findById(opdAmountDTO.getEmployee()));

            opdAmountRepository.save(opdAmount);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdAmountService -> saveOpdAmount()" + e);
        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link OpdAmount}.
     * =================================================================
     *
     * @param opdAmountDTO
     * @param id
     * @return CommonResponse
     */
    public CommonResponse updateOpdAmount(OpdAmountDTO opdAmountDTO, String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = opdAmountRepository.getById(Long.valueOf(id));

            opdAmount.setId(Long.valueOf(opdAmountDTO.getId()));
            opdAmount.setAmount(Double.valueOf(opdAmountDTO.getAmount()));
            opdAmount.setExpireDate(rewardService.stringToDate(opdAmountDTO.getExpireDate()));
            opdAmount.setEmployee(employeeService.findById(opdAmountDTO.getEmployee()));

            opdAmountRepository.save(opdAmount);
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdAmountService -> updateOpdAmount()" + e);
        }

        return  commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible delete {@link OpdAmount}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteOpdAmount(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = opdAmountRepository.getById(Long.valueOf(id));

            opdAmount.setStatuss(CommonStatus.DELETE);
            opdAmountRepository.save(opdAmount);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdAmountService -> deleteOpdAmount()" + e);
        }
        return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to get all {@link OpdAmount}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();

        List<OpdAmountDTO> opdAmountDTOS = null;

        try {

            List<OpdAmount> opdAmounts = opdAmountRepository.findAll();
            opdAmountDTOS = castopdAmountsIntoOpdAmountdDTOS(opdAmounts);

            commonResponse.setPayload(Collections.singletonList(opdAmountDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdAmountService -> getAll()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible cast opdamounts to opdamountdtos {@link OpdAmount}.
     * =================================================================
     *
     * @param opdAmounts
     * @return List
     */
    private List<OpdAmountDTO> castopdAmountsIntoOpdAmountdDTOS(List<OpdAmount> opdAmounts) {

        List<OpdAmountDTO> opdAmountDTOS = new ArrayList<>();

        for(OpdAmount opdAmount : opdAmounts){

            OpdAmountDTO opdAmountDTO;
            opdAmountDTO = modelMapper.map(opdAmount,OpdAmountDTO.class);
            opdAmountDTOS.add(opdAmountDTO);

        }
        return opdAmountDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast dtos to opdamounts {@link OpdAmount}.
     * =================================================================
     *
     * @param opdAmountDTOs
     * @return Set
     */
    public Set<OpdAmount> castopdAmountDTOSIntoOpdAmounts(Set<OpdAmountDTO> opdAmountDTOs) {

        Set<OpdAmount> opdAmounts = new HashSet<>();

        for(OpdAmountDTO opdAmountDTO : opdAmountDTOs){

            OpdAmount opdAmount;
            opdAmount = modelMapper.map(opdAmountDTO,OpdAmount.class);
            opdAmounts.add(opdAmount);

        }
        return opdAmounts;
    }

    /**
     * =================================================================
     * This method is responsible get by id {@link OpdAmount}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        OpdAmountDTO opdAmountDTO =null;

        try {

            OpdAmount opdAmount = opdAmountRepository.findById(Long.valueOf(id)).get();
            opdAmountDTO =modelMapper.map(opdAmount,OpdAmountDTO.class);
            commonResponse.setPayload(Collections.singletonList(opdAmountDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdAmountService -> getById()" + e);
        }

        return  commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible find by id {@link OpdAmount}.
     * =================================================================
     *
     * @param id
     * @return OpdAmount
     */
    public OpdAmount findById(String id){
        return opdAmountRepository.findById(Long.valueOf(id)).get();
    }


}
