package com.qpidhealth.qpid.search.repository;


import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.services.PatientService;
import org.apache.commons.io.IOUtils;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
public class PatientDaoImpl implements PatientDao {

//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public void insert(Patient newPatient) {
//        em.persist(newPatient);
//    }

    @Override
    public List<Patient> findAll() {

//        Query q = em.createQuery("select patient from Patient patient");
//        List<Patient> patients = q.getResultList();
//        return patients;

        Patient p1 = new Patient(100000L,"Mary TestPerson");
        p1.add(new PatientDocs("Patient Note","6/20/2010", "Mary_1"));
        p1.add(new PatientDocs("Patient Note","6/20/2010", "Mary_2"));

        Patient p2 = new Patient(100001L,"Joe TestPerson");
        p2.add(new PatientDocs("Clinical Note","4/6/2010","Joe_1"));
        p2.add(new PatientDocs("Summary", "7/2/2010","Joe_2"));

        Patient p3 = new Patient(100002L,"Sam TestPerson");
        p3.add(new PatientDocs("Patient Note", "8/3/2012","Sam_1"));

        List<Patient> results = new ArrayList<>();
        results.add(p1);
        results.add(p2);
        results.add(p3);
        return results;
    }
    private static String resource(String fileName) {
        ClassLoader classLoader = PatientService.class.getClassLoader();
        try {
            return IOUtils.toString(classLoader.getResourceAsStream("documents/"+fileName+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to retrieve resource "+fileName;
        }
    }
}
