package com.ems.service;

import com.ems.util.CommonResponse;
import com.ems.constant.CommonStatus;
import com.ems.dto.RewardDTO;
import com.ems.entity.Reward;
import com.ems.repository.RewardRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in RewardService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class RewardService {

    private final Logger LOGGER = LoggerFactory.getLogger(RewardService.class);

    private RewardRepository rewardRepository;
    private ModelMapper moddelMapper;
    private EmployeeService employeeService;



    @Autowired
    @Lazy
    public RewardService(RewardRepository rewardRepository, ModelMapper moddelMapper,EmployeeService employeeService) {
        this.rewardRepository = rewardRepository;
        this.moddelMapper = moddelMapper;
        this.employeeService = employeeService;
    }

    /**
     * =================================================================
     * This method is responsible save {@link Reward}.
     * =================================================================
     *
     * @param rewardDTO
     * @return
     */
    public CommonResponse saveReward(RewardDTO rewardDTO) {

        CommonResponse commonResponse = new CommonResponse();
        try {
          //  Reward reward = moddelMapper.map(rewardDTO,Reward.class);

            Reward reward = new Reward();
            reward.setRewardId(Long.valueOf(rewardDTO.getRewardId()));
            reward.setAmount(Double.parseDouble(rewardDTO.getAmount()));
            reward.setAddedDate(stringToDate(rewardDTO.getAddedDate()));
            reward.setExpireDate(stringToDate(rewardDTO.getExpireDate()));
            reward.setEmployee(employeeService.findById(rewardDTO.getEmployee()));



            rewardRepository.save(reward);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RewardService -> saveReward()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible update {@link Reward}.
     * =================================================================
     *
     * @param rewardId
     * @param rewardDTO
     * @return
     */
    public CommonResponse updateReward(String rewardId, RewardDTO rewardDTO) {
        CommonResponse commonResponse = new CommonResponse();

        try {

            Reward reward = rewardRepository.getById(Long.valueOf(rewardId));

            reward.setRewardId(Long.valueOf(rewardDTO.getRewardId()));
            reward.setAmount(Double.parseDouble(rewardDTO.getAmount()));
            reward.setAddedDate(stringToDate(rewardDTO.getAddedDate()));
            reward.setExpireDate(stringToDate(rewardDTO.getExpireDate()));
            reward.setEmployee(employeeService.findById(rewardDTO.getEmployee()));

            rewardRepository.save(reward);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RewardService -> updateReward()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to convert string to date.
     * =================================================================
     *
     * @param date
     * @return
     */
    public Date stringToDate(String date){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = null;

        try {
            date1 = format.parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }

        return date1;
    }

    /**
     * =================================================================
     * This method is responsible delete {@link Reward}.
     * =================================================================
     *
     * @param rewardId
     * @return
     */
    public CommonResponse deleteReward(String rewardId) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Reward reward = rewardRepository.getById(Long.valueOf(rewardId));

            reward.setStatuss(CommonStatus.DELETE);
            rewardRepository.save(reward);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in RewardService -> deleteReward()" + e);
        }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to get All {@link Reward}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {

     CommonResponse commonResponse = new CommonResponse();
        Set<RewardDTO> rewardDTOS = null;

     try {
         Set<Reward> rewards = (Set<Reward>) rewardRepository.findAll();
         rewardDTOS = castRewardsIntoRewardDTOS(rewards);
         commonResponse.setPayload(Collections.singletonList(rewardDTOS));
         commonResponse.setStatus(true);


     }catch (Exception e){
         LOGGER.error("/**************** Exception in RewardService -> getAll()" + e);
     }
        return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible cast rewards to dtos {@link Reward}.
     * =================================================================
     *
     * @param rewards
     * @return
     */
    private Set<RewardDTO> castRewardsIntoRewardDTOS(Set<Reward> rewards) {

        Set<RewardDTO> rewardDTOS = new HashSet<>();

        for(Reward reward : rewards){
            RewardDTO rewardDTO = moddelMapper.map(reward,RewardDTO.class);
            rewardDTOS.add(rewardDTO);
        }

        return rewardDTOS;
    }

    /**
     * =================================================================
     * This method is responsible cast dtos to rewards {@link Reward}.
     * =================================================================
     *
     * @param rewardDTOS
     * @return
     */
    public Set<Reward> castRewardDTOSIntoRewards(Set<RewardDTO> rewardDTOS) {

        Set<Reward> rewards = new HashSet<>();

        for(RewardDTO rewardDTO : rewardDTOS){
            Reward reward = moddelMapper.map(rewardDTO,Reward.class);
            rewards.add(reward);
        }

        return rewards;
    }


    /**
     * =================================================================
     * This method is responsible get By id {@link Reward}.
     * =================================================================
     *
     * @param rewardId
     * @return
     */
    public CommonResponse getById(String rewardId) {

        CommonResponse commonResponse = new CommonResponse();
        RewardDTO rewardDTO = null;
        try {

            Reward reward = rewardRepository.findById(Long.valueOf(rewardId)).get();
            rewardDTO = moddelMapper.map(reward,RewardDTO.class);
            commonResponse.setPayload(Collections.singletonList(rewardDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            LOGGER.error("/**************** Exception in RewardService -> getById()" + e);
        }
      return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to find by id {@link Reward}.
     * =================================================================
     *
     * @param rewardId
     * @return Reward
     */
    public Reward findById(String rewardId){
        return rewardRepository.findById(Long.valueOf(rewardId)).get();
    }



    public String DateTostring(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date1 = null;

        try {
            date1 = String.valueOf(format.parse(String.valueOf(date)));
        } catch (Exception e) {
            System.out.println(e);
        }

        return date1;

    }
}
