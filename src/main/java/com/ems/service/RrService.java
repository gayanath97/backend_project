package com.ems.service;

import com.ems.constant.CommonStatus;
import com.ems.dto.RrDTO;
import com.ems.entity.Rr;
import com.ems.repository.RrRepository;
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
 * This class is responsible handle all logic's in RrService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class RrService {

    private final Logger LOGGER = LoggerFactory.getLogger(RrService.class);

    private RrRepository rrRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private RrBillService rrBillService;
    private EmployeeRrService employeeRrService;

    @Autowired
    @Lazy
    public RrService(RrRepository rrRepository, ModelMapper modelMapper,EmployeeService employeeService,RrBillService rrBillService,EmployeeRrService employeeRrService) {
        this.rrRepository = rrRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.rrBillService = rrBillService;
        this.employeeRrService = employeeRrService;
    }

    /**
     * =================================================================
     * This method is responsible save {@link Rr}.
     * =================================================================
     *
     * @param rrDTO
     * @return
     */
    public CommonResponse saveRr(RrDTO rrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = new Rr();

        //    rr.setId(Long.valueOf(rrDTO.getId()));
            rr.setExtensionNo(Integer.valueOf(rrDTO.getExtensionNo()));
            rr.setCustomer(rrDTO.getCustomer());
            rr.setLocation(rrDTO.getLocation());
        //    rr.setSta_tus(rrDTO.getSta_tus());

            rr.setSta_tus("Pending");




        //    rr.setRrBills(rrBillService.castRrBillDTOSIntoRrBills(rrDTO.getRrBills()));

         //   rr.setEmployeeRrs(employeeRrService.castToEmployeeRrs(rrDTO.getEmployeeRrs()));

            rrRepository.save(rr);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrService -> saveRr()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link Rr}.
     * =================================================================
     *
     * @param id
     * @param rrDTO
     * @return
     */
    public CommonResponse updateRr(String id, RrDTO rrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = rrRepository.getById(Long.valueOf(id));

        //    rr.setId(Long.valueOf(rrDTO.getId()));
            rr.setExtensionNo(Integer.valueOf(rrDTO.getExtensionNo()));
            rr.setCustomer(rrDTO.getCustomer());
            rr.setLocation(rrDTO.getLocation());
          //  rr.setSta_tus(rrDTO.getSta_tus());
            rr.setSta_tus("Pending");




         //   rr.setRrBills(rrBillService.castRrBillDTOSIntoRrBills(rrDTO.getRrBills()));

        //    rr.setEmployeeRrs(employeeRrService.castToEmployeeRrs(rrDTO.getEmployeeRrs()));

            rrRepository.save(rr);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrService -> updateRr()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link Rr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse deleteRr(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = rrRepository.getById(Long.valueOf(id));

            rrRepository.delete(rr);

           // rr.setStatuss(CommonStatus.DELETE);
          //  rrRepository.save(rr);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrService -> deleteRr()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible get all {@link Rr}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<RrDTO> rrDTOS = null;

        try {

            List<Rr> rrs = rrRepository.findAll();

            rrDTOS = castRrListIntoRrDTOList(rrs);
            commonResponse.setPayload(Collections.singletonList(rrDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrService -> getAll()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast rrs to dtos {@link Rr}.
     * =================================================================
     *
     * @param rrs
     * @return List
     */
    private List<RrDTO> castRrListIntoRrDTOList(List<Rr> rrs) {

        List<RrDTO> rrDTOList = new ArrayList<>();

        for (Rr rr : rrs) {
            RrDTO rrDTO;
            rrDTO = modelMapper.map(rr,RrDTO.class);

            rrDTO.setId(String.valueOf(rr.getId()));
            rrDTO.setCustomer(rr.getCustomer());
            rrDTO.setExtensionNo(String.valueOf(rr.getExtensionNo()));
            rrDTO.setLocation(rr.getLocation());
            rrDTO.setSta_tus("Pending");



            rrDTOList.add(rrDTO);
        }

        return rrDTOList;


    }

    /**
     * =================================================================
     * This method is responsible cast dtos to rrs {@link Rr}.
     * =================================================================
     *
     * @param rrDTOSet
     * @return Set
     */
    public Set<Rr> castRrDTOListIntoRrList(Set<RrDTO> rrDTOSet) {

        Set<Rr> rrList = new HashSet<>();

        for (RrDTO rrDTO : rrDTOSet) {
            Rr rr;
            rr = modelMapper.map(rrDTO,Rr.class);
            rrList.add(rr);
        }

        return rrList;


    }

    /**
     * =================================================================
     * This method is responsible to get rr by id {@link Rr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse =new CommonResponse();

        RrDTO rrDTO =null;


        try {

            Rr rr = rrRepository.findById(Long.valueOf(id)).get();
            rrDTO = modelMapper.map(rr,RrDTO.class);

            commonResponse.setPayload(Collections.singletonList(rrDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RrService -> getById()" + e);
        }

        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible find by id {@link Rr}.
     * =================================================================
     *
     * @param id
     * @return
     */
    public Rr findById(String id){
        return rrRepository.findById(Long.valueOf(id)).get();
    }



}
