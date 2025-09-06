package com.orderfy.auth_service.Employee;

import com.orderfy.auth_service.Employee.dto.EmployeeCreateRequestDTO;
import com.orderfy.auth_service.Utils.FormatCpfCnpj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        employeeService.create(employeeCreateRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }



}
