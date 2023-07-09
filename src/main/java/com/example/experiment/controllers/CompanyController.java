package com.example.experiment.controllers;

import com.example.experiment.models.Company;
import com.example.experiment.models.Sector;
import com.example.experiment.services.CompanyService;
import com.example.experiment.services.EmailService;
import com.example.experiment.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService service;
    @Autowired
    private SectorService sec;
    @Autowired
    private EmailService email;


    @GetMapping("/companies")
    public ModelAndView companies() {
        ModelAndView mv = new ModelAndView("companies/index");
        mv.addObject("companies", service.companies());
        return mv;
    }


    @GetMapping("/createCompany")
    public ModelAndView createCompany() {
        Company company = new Company();
        ModelAndView mv = new ModelAndView("companies/create");
        mv.addObject("sectors", sec.listSectors());
        mv.addObject("company", company);
        return mv;
    }

    @PostMapping("/saveCompany")
    public ModelAndView saveCompany(@PathParam("sector") String sector, @Valid Company company, BindingResult result) throws MessagingException {
        ModelAndView mv = new ModelAndView("redirect:/companies");
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("companies/create");
            mav.addObject("sectors", sec.listSectors());
            return mav;
        }
        company.setSectorId(sec.findById(Long.parseLong(sector)));
        service.createCompany(company);
        return mv;
    }

    @GetMapping("/removeCompany/{id}")
    public ModelAndView removeCompany(@PathVariable(value = "id") String id) {
        service.removeCompany(service.findCompnyById(Long.parseLong(id)));
        return new ModelAndView("redirect:/companies");
    }

    @GetMapping("/editCompany/{id}")
    public ModelAndView editCompany(@PathVariable("id") String id) {

        ModelAndView mv = new ModelAndView("companies/edit");
        mv.addObject("sectors", sec.listSectors());
        mv.addObject("company", service.findCompnyById(Long.parseLong(id)));
        return mv;
    }

    @GetMapping("/updateCompany/{id}")
    public ModelAndView updateCompany(@PathVariable("id") String id, @PathParam("name") String name, @PathParam("country") String country, @PathParam("sector") String sector) {

        Company company = service.findCompnyById(Long.parseLong(id));
        company.setSectorId(sec.findById(Long.parseLong(sector)));
        company.setName(name);
        company.setCountry(country);
        service.createCompany(company);
        return new ModelAndView("redirect:/companies");
    }

    @GetMapping("/showCompany/{id}")
    public ModelAndView showCompany(@PathVariable("id") String id){
    ModelAndView mv=new ModelAndView("companies/show");
    mv.addObject("company",service.findCompnyById(Long.parseLong(id)));
    mv.addObject("branches",service.findCompnyById(Long.parseLong(id)).getBranches());
    return mv;
    }
}
