package com.qpidhealth.qpid.search.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<PatientDocs> documents; // id ::: date ::: contents

    public Patient() {}

    public Patient(String name) {
        this.name = name;
        this.documents = new ArrayList<>();
    }

    public Patient(Long id,String name) {
        this.id = id;
        this.name = name;
        this.documents = new ArrayList<>();
    }
    public static Patient create() {
        return new Patient();
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

    public void add(PatientDocs document){
        documents.add(document);
    }

    public List<PatientDocs> getDocuments() {
        return documents;
    }

}
