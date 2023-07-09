package com.example.experiment.services;

import com.example.experiment.models.Company;
import com.example.experiment.models.Sector;
import com.example.experiment.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepo;

    public Sector createSector(Sector sector) {
        return sectorRepo.save(sector);
    }

    public List<Sector> listSectors() {
        return sectorRepo.findAll();
    }

    public Sector findById(Long id) {
        return sectorRepo.findById(id).get();
    }

    public void deleteById(Long id) {
        sectorRepo.deleteById(id);
    }


}
