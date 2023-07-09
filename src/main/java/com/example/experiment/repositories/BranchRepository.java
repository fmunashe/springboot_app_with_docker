package com.example.experiment.repositories;

import com.example.experiment.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findBranchByCompanyId(Long id);
}
