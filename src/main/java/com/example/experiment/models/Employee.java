package com.example.experiment.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "*required")
    private String name;
    @NotBlank(message = "*required")
    private String position;
    @NotBlank(message = "*required")
    private String notes;
    private boolean primary_contact;
    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branchId;
    @OneToMany(mappedBy = "employeeId",cascade = CascadeType.ALL)
    private List<Contact> contacts;

    public Employee() {
    }

    public Employee(String name, String position, String notes, boolean primary_contact, Branch branchId, List<Contact> contacts) {
        this.name = name;
        this.position = position;
        this.notes = notes;
        this.primary_contact = primary_contact;
        this.branchId = branchId;
        this.contacts = contacts;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isPrimary_contact() {
        return primary_contact;
    }

    public void setPrimary_contact(boolean primary_contact) {
        this.primary_contact = primary_contact;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
