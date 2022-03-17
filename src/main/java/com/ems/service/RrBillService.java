package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.RrBillDTO;
import com.ems.entity.RrBill;
import com.ems.util.CommonResponse;
import com.ems.repository.RrBillRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in RrBillService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class RrBillService {

    private final Logger LOGGER = LoggerFactory.getLogger(RrBillService.class);

    private RrBillRepository rrBillRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private RrService rrService;

    @Autowired
    public RrBillService(RrBillRepository rrBillRepository, ModelMapper modelMapper,RewardService rewardService,RrService rrService) {
        this.rrBillRepository = rrBillRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.rrService = rrService;
    }


    /**
     * =================================================================
     * This method is responsible save {@link RrBill}.
     * =================================================================
     *
     * @param rrBillDTO
     * @return
     */
    public CommonResponse saveRrBill(RrBillDTO rrBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = new RrBill();

          //  rrBill.setId(Long.valueOf(rrBillDTO.getId()));
            rrBill.setAmount(Double.valueOf(rrBillDTO.getAmount()));
            rrBill.setParticulars(rrBillDTO.getParticulars());
            rrBill.setDate(rewardService.stringToDate(rrBillDTO.getDate()));
            rrBill.setExtensionNo(Integer.valueOf(rrBillDTO.getExtensionNo()));
            rrBill.setRr(rrService.findById(rrBillDTO.getRr()));

            rrBillRepository.save(rrBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrBillService -> saveRrBill()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link RrBill}.
     * =================================================================
     *
     * @param id
     * @param rrBillDTO
     * @return
     */
    public CommonResponse updateRrBill(String id, RrBillDTO rrBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = rrBillRepository.getById(Long.valueOf(id));

         //   rrBill.setId(Long.valueOf(rrBillDTO.getId()));
            rrBill.setAmount(Double.valueOf(rrBillDTO.getAmount()));
            rrBill.setParticulars(rrBillDTO.getParticulars());
            rrBill.setDate(rewardService.stringToDate(rrBillDTO.getDate()));
            rrBill.setExtensionNo(Integer.valueOf(rrBillDTO.getExtensionNo()));
            rrBill.setRr(rrService.findById(rrBillDTO.getRr()));

            rrBillRepository.save(rrBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrBillService -> updateRrBill()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link RrBill}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse deleteRrBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = rrBillRepository.getById(Long.valueOf(id));

           // rrBill.setStatuss(CommonStatus.DELETE);
          //  rrBillRepository.save(rrBill);

            rrBillRepository.delete(rrBill);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrBillService -> deleteRrBill()" + e);
        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible get All {@link RrBill}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<RrBillDTO> rrBillDTOS = null;

        try {

            List<RrBill> rrBills = rrBillRepository.findAll();

            rrBillDTOS = castRrBillIntoRrBillDTOS(rrBills);

            commonResponse.setPayload(Collections.singletonList(rrBillDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrBillService -> getAll()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast rrbill to dtos {@link RrBill}.
     * =================================================================
     *
     * @param rrBills
     * @return
     */
    private List<RrBillDTO> castRrBillIntoRrBillDTOS(List<RrBill> rrBills) {

        List<RrBillDTO> rrBillDTOS = new ArrayList<>();

        for(RrBill rrBill : rrBills){

           // RrBillDTO rrBillDTO = modelMapper.map(rrBill,RrBillDTO.class);

            RrBillDTO rrBillDTO = new RrBillDTO();

            rrBillDTO.setId(String.valueOf(rrBill.getId()));
            rrBillDTO.setDate(rewardService.DateTostring(rrBill.getDate()));
            rrBillDTO.setAmount(String.valueOf(rrBill.getAmount()));
            rrBillDTO.setExtensionNo(String.valueOf(rrBill.getExtensionNo()));
            rrBillDTO.setParticulars(rrBill.getParticulars());
            rrBillDTO.setRr(String.valueOf(rrBill.getRr().getId()));

            rrBillDTOS.add(rrBillDTO);

        }

     return rrBillDTOS;
}

    /**
     * =================================================================
     * This method is responsible cast dtos to bills {@link RrBill}.
     * =================================================================
     *
     * @param rrBillDTOS
     * @return
     */
    public Set<RrBill> castRrBillDTOSIntoRrBills(Set<RrBillDTO> rrBillDTOS) {

        Set<RrBill> rrBills = new HashSet<>();

        for(RrBillDTO rrBillDTO : rrBillDTOS){

            RrBill rrBill = modelMapper.map(rrBillDTO,RrBill.class);

            rrBills.add(rrBill);

        }

        return rrBills;
    }

    /**
     * =================================================================
     * This method is responsible get by id {@link RrBill}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
       // RrBillDTO rrBillDTO = null;

        try {

            RrBill rrBill = rrBillRepository.findById(Long.valueOf(id)).get();

           // rrBillDTO = modelMapper.map(rrBill,RrBillDTO.class);

            RrBillDTO rrBillDTO = new RrBillDTO();

            rrBillDTO.setId(String.valueOf(rrBill.getId()));
            rrBillDTO.setAmount(String.valueOf(rrBill.getAmount()));
            rrBillDTO.setParticulars(rrBill.getParticulars());
            rrBillDTO.setDate(rewardService.DateTostring(rrBill.getDate()));
            rrBillDTO.setExtensionNo(String.valueOf(rrBill.getExtensionNo()));
            rrBillDTO.setRr(String.valueOf(rrBill.getRr().getId()));

            commonResponse.setPayload(Collections.singletonList(rrBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrBillService -> getById()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible find by id {@link RrBill}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public RrBill findById(String id){
        return rrBillRepository.findById(Long.valueOf(id)).get();
    }

}
