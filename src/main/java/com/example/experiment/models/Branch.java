package com.example.experiment.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "*required")
    private String name;
    @NotBlank(message = "*required")
    @Email(message = "valid email required")
    private String email;
    @NotBlank(message = "*required")
    private String phone;
    @NotBlank(message = "*required")
    private String address;
    @NotBlank(message = "*required")
    private String country;
    private boolean head_office;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;
    @OneToMany(mappedBy = "branchId",cascade = CascadeType.ALL)
    private List<Employee>employees;
    public Branch() {
    }

    public Branch(String name, String email, String phone, String address, String country, boolean head_office, Company companyId, List<Employee> employees) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.head_office = head_office;
        this.companyId = companyId;
        this.employees = employees;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isHead_office() {
        return head_office;
    }

    public void setHead_office(boolean head_office) {
        this.head_office = head_office;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
