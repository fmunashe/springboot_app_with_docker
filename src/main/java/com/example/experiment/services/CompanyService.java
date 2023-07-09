package com.example.experiment.services;

import com.example.experiment.models.Company;
import com.example.experiment.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repo;

    public Company createCompany(Company comp) {
        return repo.save(comp);
    }

    public List<Company> companies() {
        return repo.findAll();
    }

    public Company findCompnyById(Long id) {
        return repo.findById(id).get();
    }

    public void removeCompany(Company id) {
        repo.delete(id);
    }

}
