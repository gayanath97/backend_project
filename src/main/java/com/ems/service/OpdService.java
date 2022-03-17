package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.util.CommonResponse;
import com.ems.dto.OpdDTO;
import com.ems.entity.Opd;
import com.ems.repository.OpdRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in OpdService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class OpdService {

    private final Logger LOGGER = LoggerFactory.getLogger(OpdService.class);

    private OpdRepository opdRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private EmployeeService employeeService;
    private OpdBillService opdBillService;
    private EmployeeOpdService employeeOpdService;

    @Autowired
    @Lazy
    public OpdService(OpdRepository opdRepository, ModelMapper modelMapper,RewardService rewardService,EmployeeService employeeService,OpdBillService opdBillService,EmployeeOpdService employeeOpdService) {
        this.opdRepository = opdRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.employeeService = employeeService;
        this.opdBillService = opdBillService;
        this.employeeOpdService = employeeOpdService;
    }

    /**
     * =================================================================
     * This method is responsible save {@link Opd}.
     * =================================================================
     *
     * @param opdDTO
     * @return CommonResponse
     */
    public CommonResponse saveOpd(OpdDTO opdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Opd opd = new Opd();

        //    opd.setId(Long.valueOf(opdDTO.getId()));
            opd.setDate(rewardService.stringToDate(opdDTO.getDate()));
            opd.setAmount(Double.valueOf(opdDTO.getAmount()));
            opd.setParticulars(opdDTO.getParticulars());
            opd.setSta_tus("pending");

//            opd.setOpdBills(opdBillService.opdBillDTOSIntoOpds(opdDTO.getOpdBills()));
          //  opd.setEmployeeOpds(employeeOpdService.castToEmployeeOpds(opdDTO.getEmployeeOpds()));


            opdRepository.save(opd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdService -> saveOpd()" + e);
        }
       return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link Opd}.
     * =================================================================
     *
     * @param id
     * @param opdDTO
     * @return
     */
    public CommonResponse updateOpd(String id, OpdDTO opdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Opd opd = opdRepository.getById(Long.valueOf(id));

          //  opd.setId(Long.valueOf(opdDTO.getId()));
            opd.setDate(rewardService.stringToDate(opdDTO.getDate()));
            opd.setAmount(Double.valueOf(opdDTO.getAmount()));
            opd.setParticulars(opdDTO.getParticulars());
            opd.setSta_tus("pending");

//            opd.setOpdBills(opdBillService.opdBillDTOSIntoOpds(opdDTO.getOpdBills()));
//            opd.setEmployeeOpds(employeeOpdService.castToEmployeeOpds(opdDTO.getEmployeeOpds()));

            opdRepository.save(opd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdService -> updateOpd()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link Opd}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteOpd(String id) {

      CommonResponse commonResponse = new CommonResponse();
      try {

          Opd opd = opdRepository.getById(Long.valueOf(id));

         opdRepository.delete(opd);
//          opd.setStatuss(CommonStatus.DELETE);
  //        opdRepository.save(opd);

          commonResponse.setStatus(true);

      }catch (Exception e){
          LOGGER.error("/**************** Exception in OpdService -> deleteOpd()" + e);
      }

     return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible getAll {@link Opd}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

         CommonResponse commonResponse = new CommonResponse();
        List<OpdDTO> opdDTOS = null;

         try {
             List<Opd> opds = opdRepository.findAll();

             opdDTOS = opdsTOopdDTOS(opds);

             commonResponse.setPayload(Collections.singletonList(opdDTOS));
             commonResponse.setStatus(true);


         }catch (Exception e){
             LOGGER.error("/**************** Exception in OpdService -> getAll()" + e);
         }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast opds to opddtos {@link Opd}.
     * =================================================================
     *
     * @param opds
     * @return
     */
    private List<OpdDTO> opdsTOopdDTOS(List<Opd> opds) {

        List<OpdDTO> opdDTOS = new ArrayList<>();

        for(Opd opd : opds){

//            OpdDTO opdDTO = modelMapper.map(opd,OpdDTO.class);

            OpdDTO opdDTO = new OpdDTO();

            opdDTO.setId(String.valueOf(opd.getId()));
            opdDTO.setDate(rewardService.DateTostring(opd.getDate()));
            opdDTO.setAmount(String.valueOf(opd.getAmount()));
            opdDTO.setParticulars(opd.getParticulars());
            opdDTO.setSta_tus("pending");

            opdDTOS.add(opdDTO);
        }
       return  opdDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast dtos to opds {@link Opd}.
     * =================================================================
     *
     * @param opdDTOs
     * @return
     */
    public Set<Opd> opdDTOSTOopds(Set<OpdDTO> opdDTOs) {

        Set<Opd> opds = new HashSet<>();

        for(OpdDTO opdDTO : opdDTOs){
            Opd opd = modelMapper.map(opdDTO,Opd.class);
            opds.add(opd);
        }
        return  opds;
    }

    /**
     * =================================================================
     * This method is responsible getById {@link Opd}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

       CommonResponse commonResponse = new CommonResponse();
     //  OpdDTO opdDTO = null;

        try {

            Opd opd = opdRepository.findById(Long.valueOf(id)).get();

          //  opdDTO = modelMapper.map(opd,OpdDTO.class);

            OpdDTO opdDTO = new OpdDTO();

            opdDTO.setId(String.valueOf(opd.getId()));
            opdDTO.setDate(rewardService.DateTostring(opd.getDate()));
            opdDTO.setAmount(String.valueOf(opd.getAmount()));
            opdDTO.setParticulars(opd.getParticulars());
            opdDTO.setSta_tus("pending");

            commonResponse.setPayload(Collections.singletonList(opdDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in OpdService -> getById()" + e);
        }
     return  commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to find By Id {@link Opd}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public Opd findById(String id){
           return opdRepository.findById(Long.valueOf(id)).get();
    }

}
