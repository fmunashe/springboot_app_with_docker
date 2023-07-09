package com.example.experiment.repositories;

import com.example.experiment.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    List<Contact> findContactByEmployeeId(Long id);
}
