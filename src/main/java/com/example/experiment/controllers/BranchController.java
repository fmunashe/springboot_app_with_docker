package com.example.experiment.controllers;

import com.example.experiment.models.Branch;
import com.example.experiment.services.BranchService;
import com.example.experiment.services.CompanyService;
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
public class BranchController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/branches")
    public ModelAndView branches() {
        ModelAndView mv = new ModelAndView("branches/index");
        mv.addObject("branches", branchService.listAllBranches());
        return mv;
    }

    @GetMapping("/createBranch")
    public ModelAndView createBranch() {
        Branch branch = new Branch();
        ModelAndView mv = new ModelAndView("branches/create");
        mv.addObject("companies", companyService.companies());
        mv.addObject("branch", branch);
        return mv;
    }

    @PostMapping("/saveBranch")
    public ModelAndView saveBranch(@PathParam("companyId") String companyId, @Valid Branch branch, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/branches");
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("branches/create");
            mav.addObject("companies", companyService.companies());
            return mav;
        }
        branch.setCompanyId(companyService.findCompnyById(Long.parseLong(companyId)));
        branchService.createBranch(branch);
        return mv;
    }

    @GetMapping("/removeBranch/{id}")
    public ModelAndView removeBranch(@PathVariable("id") String id) {
        branchService.removeBranch(Long.parseLong(id));
        return new ModelAndView("redirect:/branches");
    }

    @GetMapping("/editBranch/{id}")
    public ModelAndView editBranch(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("branches/edit");
        mv.addObject("companies", companyService.companies());
        mv.addObject("branch", branchService.findBranchById(Long.parseLong(id)));
        return mv;
    }

    @GetMapping("/updateBranch/{id}")
    public ModelAndView updateBranch(@PathVariable("id") String id, @PathParam("name") String name, @PathParam("email") String email, @PathParam("phone") String phone, @PathParam("companyId") String companyId, @PathParam("head_office") boolean head_office, @PathParam("country") String country, @PathParam("address") String address) {

        Branch branch = branchService.findBranchById(Long.parseLong(id));
        branch.setCompanyId(companyService.findCompnyById(Long.parseLong(companyId)));
        branch.setAddress(address);
        branch.setCountry(country);
        branch.setEmail(email);
        branch.setHead_office(head_office);
        branch.setPhone(phone);
        branch.setName(name);
        branchService.createBranch(branch);
        return new ModelAndView("redirect:/branches");
    }

    @GetMapping("/showBranch/{id}")
    public ModelAndView showBranch(@PathVariable("id") String id){
        ModelAndView mv=new ModelAndView("branches/show");
        mv.addObject("branch",branchService.findBranchById(Long.parseLong(id)));
        mv.addObject("employees",branchService.findBranchById(Long.parseLong(id)).getEmployees());
        return mv;
    }
}
