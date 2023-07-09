package com.example.experiment.services;

import com.example.experiment.exceptions.ResourceNotFoundException;
import com.example.experiment.models.Branch;
import com.example.experiment.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public Branch createBranch(Branch branch) {
        return branchRepo.save(branch);
    }

    public List<Branch> listAllBranches() {
        return branchRepo.findAll();
    }

    public Branch findBranchById(Long id) {

        return branchRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find the branch with ID of " + id));
    }

    public void removeBranch(Long id) {
        branchRepo.deleteById(id);
    }

    public List<Branch> findBranchByCompanyId(Long id) {
        return branchRepo.findBranchByCompanyId(id);
    }
}
