package com.example.experiment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.experiment.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	public Supplier findSupplierByTradeName(String tradeName);
}
