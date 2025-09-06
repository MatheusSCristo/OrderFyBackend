package com.orderfy.auth_service.Employee;

import com.orderfy.auth_service.Employee.dto.EmployeeCreateRequestDTO;
import com.orderfy.auth_service.Restaurant.Restaurant;
import com.orderfy.auth_service.Restaurant.RestaurantService;
import com.orderfy.auth_service.Utils.FormatCpfCnpj;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestaurantService restaurantService;
    private final PasswordEncoder passwordEncoder;

    public void create(EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        System.out.println(employeeCreateRequestDTO.toString());
        Restaurant restaurant= restaurantService.findById(employeeCreateRequestDTO.restaurantId());
        String cpf= FormatCpfCnpj.formatCnpj(employeeCreateRequestDTO.cpf());
        Employee employee=new Employee();

        String encodedPassword= passwordEncoder.encode(employeeCreateRequestDTO.password());
        employee.setCpf(cpf);
        employee.setName(employeeCreateRequestDTO.name());
        employee.setLastName(employeeCreateRequestDTO.lastName());
        employee.setRole(employeeCreateRequestDTO.role());
        employee.setRestaurant(restaurant);
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
    }


}
