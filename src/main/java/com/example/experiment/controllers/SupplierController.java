package com.example.experiment.controllers;

import com.example.experiment.models.Supplier;
import com.example.experiment.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping("/")
    public ModelAndView layout() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("suppliers", service.listSuppliers());
        return mv;
    }

    @GetMapping("/createSupplier")
    public ModelAndView createSupplier() {
        Supplier supplier=new Supplier();
        ModelAndView mv = new ModelAndView("createSupplier");
        mv.addObject("supplier",supplier);
        return mv;
    }

    @PostMapping("/saveSupplier")
    public ModelAndView saveSupplier(@Valid Supplier sp, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/suppliers");
        if (result.hasErrors()) {
            return new ModelAndView("createSupplier");
        }
        service.createSupplier(sp);
        return mv;
    }

    @GetMapping("/removeSupplier/{id}")
    public ModelAndView removeSupplier(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("redirect:/suppliers");
        System.out.println(id);
        service.removeSupplier(Long.parseLong(id));
        return mv;
    }

    @GetMapping("/suppliers")
    public ModelAndView listSuppliers() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("suppliers", service.listSuppliers());
        return mv;
    }

//    @GetMapping("/suppliersReport/{format}")
//    public String suppliersReport(@PathVariable("format") String format) throws FileNotFoundException, JRException {
//        return service.supplierReport(format);
//    }
}
