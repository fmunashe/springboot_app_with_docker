package com.example.experiment.controllers;

import com.example.experiment.models.Employee;
import com.example.experiment.services.BranchService;
import com.example.experiment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
public class EmployeeController {
    @Autowired
    private BranchService branchService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ModelAndView employees() {
        ModelAndView mv = new ModelAndView("employees/index");
        mv.addObject("employees", employeeService.listAllEmployees());
        return mv;
    }

    @GetMapping("/createEmployee")
    public ModelAndView createEmployee() {
        ModelAndView mv = new ModelAndView("employees/create");
        Employee employee = new Employee();
        mv.addObject("branches", branchService.listAllBranches());
        mv.addObject("employee", employee);
        return mv;
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@PathParam("branchId") String branchId, @Valid Employee employee, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("employees/create");
            mav.addObject("branches", branchService.listAllBranches());
            return mav;
        }
        employee.setBranchId(branchService.findBranchById(Long.parseLong(branchId)));
        employeeService.createEmployee(employee);
        return mv;
    }

    @GetMapping("/removeEmployee/{id}")
    public ModelAndView removeEmployee(@PathVariable("id") String id) {
        employeeService.removeEmployee(Long.parseLong(id));
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/editEmployee/{id}")
    public ModelAndView editEmployee(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("employees/edit");
        mv.addObject("branches", branchService.listAllBranches());
        mv.addObject("employee", employeeService.findByEmployeeId(Long.parseLong(id)));
        return mv;
    }

    @GetMapping("/updateEmployee/{id}")
    public ModelAndView updateEmployee(@PathVariable("id") String id, @PathParam("name") String name, @PathParam("position") String position, @PathParam("primary_contact") boolean primary_contact, @PathParam("branchId") String branchId, @PathParam("notes") String notes) {

        Employee employee = employeeService.findByEmployeeId(Long.parseLong(id));
        employee.setBranchId(branchService.findBranchById(Long.parseLong(branchId)));
        employee.setName(name);
        employee.setPosition(position);
        employee.setPrimary_contact(primary_contact);
        employee.setNotes(notes);
        employeeService.createEmployee(employee);
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("showEmployee/{id}")
    public ModelAndView showEmployee(@PathVariable("id") String id){
        ModelAndView mv=new ModelAndView("employees/show");
        mv.addObject("employee",employeeService.findByEmployeeId(Long.parseLong(id)));
        mv.addObject("contacts",employeeService.findByEmployeeId(Long.parseLong(id)).getContacts());
        return mv;
    }

}
