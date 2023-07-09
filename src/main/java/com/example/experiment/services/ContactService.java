package com.example.experiment.services;

import com.example.experiment.models.Contact;
import com.example.experiment.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;

    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public List<Contact> listAllContacts() {
        return contactRepo.findAll();
    }

    public Contact findByContactId(Long id) {
        return contactRepo.findById(id).get();
    }

    public void removeContact(Long id) {
        contactRepo.deleteById(id);
    }

    public List<Contact> findContactByEmployeeId(Long id){
        return contactRepo.findContactByEmployeeId(id);
    }
}
