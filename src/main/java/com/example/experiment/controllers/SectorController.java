package com.example.experiment.controllers;

import com.example.experiment.models.Sector;
import com.example.experiment.services.CompanyService;
import com.example.experiment.services.SectorService;
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
public class SectorController {

    @Autowired
    private SectorService service;
    @Autowired
    CompanyService companyService;

    @GetMapping("/sectors")
    public ModelAndView listSectors() {
        ModelAndView mv = new ModelAndView("sectors/index");
        mv.addObject("sectors", service.listSectors());
        return mv;
    }

    @GetMapping("/createSector")
    public ModelAndView createSector() {
        Sector sector = new Sector();
        ModelAndView mv = new ModelAndView("sectors/create");
        mv.addObject("sector", sector);
        return mv;
    }

    @PostMapping("/saveSector")
    public ModelAndView saveSector(@Valid Sector sector, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("sectors/create");
        }
        service.createSector(sector);
        return new ModelAndView("sectors/create");
    }

    @GetMapping("/editSector/{id}")
    public ModelAndView editSector(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("sectors/edit");
        mv.addObject("sector", service.findById(Long.parseLong(id)));
        return mv;
    }

    @GetMapping("/removeSector/{id}")
    public ModelAndView removeSector(@PathVariable("id") String id) {
        service.deleteById(Long.parseLong(id));
        return new ModelAndView("redirect:/sectors");
    }

    @GetMapping("/updateSector/{id}")
    public ModelAndView updateSector(@PathVariable("id") String id, @PathParam("name") String name) {
        Sector sector = service.findById(Long.parseLong(id));
        sector.setName(name);
        service.createSector(sector);
        return new ModelAndView("redirect:/sectors");
    }

    @GetMapping("/showSector/{id}")
    public ModelAndView showSector(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("sectors/show");
        mv.addObject("sector", service.findById(Long.parseLong(id)));
        mv.addObject("companies",service.findById(Long.parseLong(id)).getCompanies() );
        return mv;
    }
}
