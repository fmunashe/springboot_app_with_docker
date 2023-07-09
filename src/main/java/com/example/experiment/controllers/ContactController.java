package com.example.experiment.controllers;

import com.example.experiment.models.Contact;
import com.example.experiment.services.ContactService;
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
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/contacts")
    public ModelAndView contacts() {
        ModelAndView mv = new ModelAndView("contacts/index");
        mv.addObject("contacts", contactService.listAllContacts());
        return mv;
    }

    @GetMapping("/createContact")
    public ModelAndView createContact() {
        Contact contact = new Contact();
        ModelAndView mv = new ModelAndView("contacts/create");
        mv.addObject("contact", contact);
        mv.addObject("employees", employeeService.listAllEmployees());
        return mv;
    }

    @PostMapping("/saveContact")
    public ModelAndView saveContact(@PathParam("employeeId") String employeeId, @Valid Contact contact, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/contacts");
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("contacts/create");
            mav.addObject("employees", employeeService.listAllEmployees());
            return mav;
        }
        contact.setEmployeeId(employeeService.findByEmployeeId(Long.parseLong(employeeId)));
        contactService.createContact(contact);
        return mv;
    }

    @GetMapping("/removeContact/{id}")
    public ModelAndView removeContact(@PathVariable("id") String id) {
        contactService.removeContact(Long.parseLong(id));
        return new ModelAndView("redirect:/contacts");
    }

    @GetMapping("/editContact/{id}")
    public ModelAndView editContact(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("contacts/edit");
        mv.addObject("employees", employeeService.listAllEmployees());
        mv.addObject("contact", contactService.findByContactId(Long.parseLong(id)));
        return mv;
    }

    @GetMapping("/updateContact/{id}")
    public ModelAndView updateContact(@PathVariable("id") String id, @PathParam("email") String email, @PathParam("phone") String phone, @PathParam("employeeId") String employeeId) {
        Contact contact = contactService.findByContactId(Long.parseLong(id));
        contact.setEmployeeId(employeeService.findByEmployeeId(Long.parseLong(employeeId)));
        contact.setEmail(email);
        contact.setPhone(phone);
        contactService.createContact(contact);
        return new ModelAndView("redirect:/contacts");
    }
}
