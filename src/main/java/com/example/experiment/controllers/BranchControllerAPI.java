package com.example.experiment.controllers;

import com.example.experiment.models.Branch;
import com.example.experiment.services.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BranchControllerAPI {

    private final BranchService branchService;

    public BranchControllerAPI(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "/branch/{branchId}")
    public Branch getBranch(@PathVariable(value = "branchId") Long branchId) {
        return branchService.findBranchById(branchId);
    }

    @GetMapping(value = "/branches")
    public List<Branch> getBranches() {
        return branchService.listAllBranches();
    }

    @PostMapping(value = "/createBranch")
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }
}
