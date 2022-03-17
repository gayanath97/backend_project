package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.entity.OpdBill;
import com.ems.util.CommonResponse;
import com.ems.dto.OpdBillDTO;
import com.ems.repository.OpdBillRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in OpdBillService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class OpdBillService {

    private final Logger LOGGER = LoggerFactory.getLogger(OpdBillService.class);

    private OpdBillRepository opdBillRepository;
    private ModelMapper modelMapper;
    private  RewardService rewardService;
    private OpdService opdService;

    @Autowired
    public OpdBillService(OpdBillRepository opdBillRepository, ModelMapper modelMapper,RewardService rewardService,OpdService opdService) {
        this.opdBillRepository = opdBillRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.opdService = opdService;
    }

    /**
     * =================================================================
     * This method is responsible save {@link OpdBill}.
     * =================================================================
     *
     * @param opdBillDTO
     * @return CommonResponse
     */
    public CommonResponse saveOpdBill(OpdBillDTO opdBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = new OpdBill();

        //    opdBill.setId(Long.valueOf(opdBillDTO.getId()));
            opdBill.setAmount(Double.valueOf(opdBillDTO.getAmount()));
            opdBill.setDate(rewardService.stringToDate(opdBillDTO.getDate()));
            opdBill.setParticulars(opdBillDTO.getParticulars());
            opdBill.setSta_tus("pending");

          //  opdBill.setOpd(opdService.findById(opdBillDTO.getOpd()));

            opdBillRepository.save(opdBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdBillService -> saveOpdBill()" + e);
        }

        return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link OpdBill}.
     * =================================================================
     *
     * @param id
     * @param opdBillDTO
     * @return CommonResponse
     */
    public CommonResponse updateOpdBill(String id, OpdBillDTO opdBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = opdBillRepository.getById(Long.valueOf(id));

        //    opdBill.setId(Long.valueOf(opdBillDTO.getId()));
            opdBill.setAmount(Double.valueOf(opdBillDTO.getAmount()));
            opdBill.setDate(rewardService.stringToDate(opdBillDTO.getDate()));
            opdBill.setParticulars(opdBillDTO.getParticulars());
            opdBill.setSta_tus("pending");

        //    opdBill.setOpd(opdService.findById(opdBillDTO.getOpd()));

            opdBillRepository.save(opdBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdBillService -> updateOpdBill()" + e);
        }

        return  commonResponse;


    }

    /**
     * =================================================================
     * This method is responsible delete {@link OpdBill}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteOpdBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = opdBillRepository.getById(Long.valueOf(id));

       //     opdBill.setStatuss(CommonStatus.DELETE);
       //     opdBillRepository.save(opdBill);

            opdBillRepository.delete(opdBill);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdBillService -> deleteOpdBill()" + e);
        }

        return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to get all {@link OpdBill}.
     * =================================================================
     *
     * @return CommonResponse
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<OpdBillDTO> opdBillDTOList = null;

        try {

            List<OpdBill> opdBills = opdBillRepository.findAll();

            opdBillDTOList = opdBillsIntoOpdDTOS(opdBills);

            commonResponse.setPayload(Collections.singletonList(opdBillDTOList));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdBillService -> getAll()" + e);
        }
        return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to casr bills to billdtos {@link OpdBill}.
     * =================================================================
     *
     * @param opdBills
     * @return List
     */
    private List<OpdBillDTO> opdBillsIntoOpdDTOS(List<OpdBill> opdBills) {

        List<OpdBillDTO> opdBillDTOS = new ArrayList<>();

        for(OpdBill opdBill : opdBills){
            OpdBillDTO opdBillDTO = modelMapper.map(opdBill,OpdBillDTO.class);
            opdBillDTOS.add(opdBillDTO);
        }
      return  opdBillDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast billdtos to bills {@link OpdBill}.
     * =================================================================
     *
     * @param opdBillDTOS
     * @return Set
     */
    public Set<OpdBill> opdBillDTOSIntoOpds(Set<OpdBillDTO> opdBillDTOS) {

        Set<OpdBill> opdBills = new HashSet<>();

        for(OpdBillDTO opdBillDTO : opdBillDTOS){
            OpdBill opdBill = modelMapper.map(opdBillDTO,OpdBill.class);
            opdBills.add(opdBill);
        }
        return  opdBills;
    }

    /**
     * =================================================================
     * This method is responsible to get by id {@link OpdBill}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        OpdBillDTO opdBillDTO = null;

        try {

            OpdBill opdBill = opdBillRepository.findById(Long.valueOf(id)).get();
            opdBillDTO = modelMapper.map(opdBill,OpdBillDTO.class);

            commonResponse.setPayload(Collections.singletonList(opdBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdBillService -> getById()" + e);
        }
        return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to find by id {@link OpdBill}.
     * =================================================================
     *
     * @param id
     * @return OpdBill
     */
    public OpdBill findById(String id){
        return  opdBillRepository.findById(Long.valueOf(id)).get();
    }
}
