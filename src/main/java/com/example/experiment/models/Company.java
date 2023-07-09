package com.example.experiment.models;

import groovy.transform.builder.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.security.cert.CertPathBuilder;
import java.util.List;

@Entity
@Builder
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "*required")
    private String name;
    @NotBlank(message = "*required")
    private String country;
    @ManyToOne
    @JoinColumn(name = "sectorId")
    private Sector sectorId;
    @OneToMany(mappedBy = "companyId",cascade = CascadeType.ALL)
    private List<Branch> branches;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Company() {
    }

    public Company(@NotBlank(message = "*required") String name, @NotBlank(message = "*required") String country, Sector sectorId, List<Branch> branches) {
        this.name = name;
        this.country = country;
        this.sectorId = sectorId;
        this.branches = branches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Sector getSectorId() {
        return sectorId;
    }

    public void setSectorId(Sector sectorId) {
        this.sectorId = sectorId;
    }
}
