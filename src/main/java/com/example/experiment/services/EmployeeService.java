package com.example.experiment.services;

import com.example.experiment.models.Employee;
import com.example.experiment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee createEmployee(Employee emp) {
        return employeeRepo.save(emp);
    }

    public List<Employee> listAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findByEmployeeId(Long id) {
        return employeeRepo.findById(id).get();
    }

    public void removeEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> findEmployeeByBranchId(Long id){
        return employeeRepo.findEmployeeByBranchId(id);
    }
}
