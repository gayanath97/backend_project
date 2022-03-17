package com.ems.service;

import com.ems.constant.ERole;
//import com.ems.dto.EmployeeDTO;
import com.ems.dto.SignupRequest;
import com.ems.entity.Employee;
import com.ems.entity.Role;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.RoleRepository;
import com.ems.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * ========================================================================
 * This class is responsible handle all logic's in EmployeeService.
 * ========================================================================
 *
 * @author Gayanath L Silva
 * @date 2022.01.30
 */
@Service
public class EmployeeService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private ExpenseService expenseService;
    private RrService rrService;
    private OpdService opdService;
    private OpdAmountService opdAmountService;
    private EmployeeExpenseService employeeExpenseService;
    private EmployeeOpdService employeeOpdService;
    private EmployeeRrService employeeRrService;
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, RewardService rewardService,ExpenseService expenseService,RrService rrService,OpdService opdService,OpdAmountService opdAmountService,EmployeeExpenseService employeeExpenseService, EmployeeOpdService employeeOpdService,EmployeeRrService employeeRrService,RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.expenseService = expenseService;
        this.rrService = rrService;
        this.opdService = opdService;
        this.opdAmountService = opdAmountService;
        this.employeeExpenseService = employeeExpenseService;
        this.employeeOpdService = employeeOpdService;
        this.employeeRrService = employeeRrService;
        this.roleRepository = roleRepository;
    }

    /**
     * =================================================================
     * This method is responsible save {@link Employee}.
     * =================================================================
     *
     * @param  signUpRequest
     * @return CommonResponse
     */
    public CommonResponse saveEmployee(SignupRequest signUpRequest) {
        LOGGER.info("******************** inside the save");
        CommonResponse commonResponse = new CommonResponse();

        try {

            Employee employee = new Employee();

            employee.setEmail(signUpRequest.getEmail());
            employee.setFirstName(signUpRequest.getFirstName());
            employee.setLastName(signUpRequest.getLastName());
            employee.setUserName(signUpRequest.getUsername());
            employee.setPassword(encoder.encode(signUpRequest.getPassword()));
            employee.setPhoneNumber(signUpRequest.getPhoneNumber());


            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "manager":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            employee.setRoles(roles);

//            employee.setRewards(rewardService.castRewardDTOSIntoRewards(employeeDTO.getRewards()));
//
//            employee.setOpdAmounts(opdAmountService.castopdAmountDTOSIntoOpdAmounts(employeeDTO.getOpdAmounts()));
//
//            employee.setEmployeeExpenses(employeeExpenseService.castToEmployeeExpenses(employeeDTO.getEmployeeExpenses()));
//            employee.setEmployeeOpds(employeeOpdService.castToEmployeeOpds(employeeDTO.getEmployeeOpds()));
//            employee.setEmployeeRrs(employeeRrService.castToEmployeeRrs(employeeDTO.getEmployeeRrs()));

            employeeRepository.save(employee);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeService -> saveEmployee()" + e);
        }
       return commonResponse;
    }


    /**
     * =================================================================
     * This method is responsible to update {@link Employee}.
     * =================================================================
     *
     * @param id,
     * @param signUpRequest
     * @return CommonResponse
     */
    public CommonResponse updateEmployee(String id, SignupRequest signUpRequest) {

        CommonResponse commonResponse = new CommonResponse();
        try {

            Employee employee = employeeRepository.getById(Long.valueOf(id));


            employee.setEmail(signUpRequest.getEmail());
            employee.setFirstName(signUpRequest.getFirstName());
            employee.setLastName(signUpRequest.getLastName());
            employee.setUserName(signUpRequest.getUsername());
            employee.setPassword(encoder.encode(signUpRequest.getPassword()));
            employee.setPhoneNumber(signUpRequest.getPhoneNumber());


            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "manager":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            employee.setRoles(roles);

//            employee.setRewards(rewardService.castRewardDTOSIntoRewards(employeeDTO.getRewards()));
//
//            employee.setOpdAmounts(opdAmountService.castopdAmountDTOSIntoOpdAmounts(employeeDTO.getOpdAmounts()));
//
//            employee.setEmployeeExpenses(employeeExpenseService.castToEmployeeExpenses(employeeDTO.getEmployeeExpenses()));
//            employee.setEmployeeOpds(employeeOpdService.castToEmployeeOpds(employeeDTO.getEmployeeOpds()));
//            employee.setEmployeeRrs(employeeRrService.castToEmployeeRrs(employeeDTO.getEmployeeRrs()));

            employeeRepository.save(employee);
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeService -> updateEmployee()" + e);
        }

        return commonResponse;

    }

    /**
     * =================================================================
     * This method is responsible to delete {@link Employee}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse deleteEmployee(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Employee employee = employeeRepository.getById(Long.valueOf(id));

//            employee.setStatus(CommonStatus.DELETE);
//            employeeRepository.save(employee);

            employeeRepository.delete(employee);

            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeService -> deleteEmployee()" + e);
        }
      return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to get all {@link Employee}.
     * =================================================================
     *
     * @return
     */
    public CommonResponse getAll() {
       CommonResponse commonResponse = new CommonResponse();
        List<SignupRequest> signupRequests = null;

       try {
           List<Employee> employees = employeeRepository.findAll();
           signupRequests = castEmployeesIntoEmployeeDTOS(employees);
           commonResponse.setPayload(Collections.singletonList(signupRequests));
           commonResponse.setStatus(true);

       }catch (Exception e){
           LOGGER.error("/**************** Exception in EmployeeService -> getAll()" + e);
       }
     return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible for cast employees to employeeDTOS {@link Employee}.
     * =================================================================
     *
     * @param employees
     * @return List<EmployeeDTO>
     */
    private List<SignupRequest> castEmployeesIntoEmployeeDTOS(List<Employee> employees) {

        List<SignupRequest> employeeDTOS = new ArrayList<>();

        for(Employee employee : employees){

            SignupRequest signupRequest;
            signupRequest = modelMapper.map(employee,SignupRequest.class);
            employeeDTOS.add(signupRequest);

        }

        return employeeDTOS;
    }

    /**
     * =================================================================
     * This method is responsible to get by id {@link Employee}.
     * =================================================================
     *
     * @param id
     * @return CommonResponse
     */
    public CommonResponse getById(String id) {

        CommonResponse commonResponse =new CommonResponse();
        SignupRequest signupRequest = null;
        try {

            Employee employee = employeeRepository.findById(Long.valueOf(id)).get();
            signupRequest=modelMapper.map(employee,SignupRequest.class);
            commonResponse.setPayload(Collections.singletonList(signupRequest));
            commonResponse.setStatus(true);

        }catch (Exception e){
            LOGGER.error("/**************** Exception in EmployeeService -> getById()" + e);
        }
      return commonResponse;
    }

    /**
     * =================================================================
     * This method is responsible to find by id {@link Employee}.
     * =================================================================
     *
     * @param id
     * @return Employee
     */
    public  Employee findById(String id){
        return employeeRepository.findById(Long.valueOf(id)).get();
    }

}
