package com.example.experiment.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "*required")
    private String name;
    @OneToMany(mappedBy = "sectorId",cascade = CascadeType.ALL)
    private List<Company> companies;
    public Sector() {
    }

    public Sector(@NotBlank(message = "Sector name Cannot be blank") String name, List<Company> companies) {
        this.name = name;
        this.companies = companies;
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

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
