package com.example.experiment.seeder;

import com.example.experiment.models.Company;
import com.example.experiment.models.Sector;
import com.example.experiment.services.CompanyService;
import com.example.experiment.services.SectorService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GenericDBSeeder implements CommandLineRunner {
    private final SectorService sectorService;
    private final CompanyService companyService;

    public GenericDBSeeder(SectorService sectorService, CompanyService companyService) {
        this.sectorService = sectorService;
        this.companyService = companyService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSectors();
        loadCompanies();
    }

    private void loadSectors() {
        List<String> sectors = Arrays.asList("Banking & Finance", "Insurance", "FinTech", "Manufacturing", "Fiduciary");
        sectors.forEach(sector -> sectorService.createSector(new Sector(sector, new ArrayList<>()))
        );
    }

    private void loadCompanies() {
        List<String> companies = Arrays.asList("Ecocash Holdings", "Econet Wireless", "Steward bank", "Delta Beverages");
        companies.forEach(company -> {
            Sector sector = sectorService.findById(RandomUtils.nextLong(1L, 5L));
            Company company1 = new Company(company, "Zimbabwe", sector, new ArrayList<>());
            companyService.createCompany(company1);
        });
    }
}
