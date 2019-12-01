package com.qpidhealth.qpid.search.services;

import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.repository.PatientDao;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PatientServiceImpl implements PatientService{
    @Inject
    private PatientDao patientDao;

    public PatientServiceImpl() {
    }

    public PatientServiceImpl(PatientDao dao) {
        this.patientDao = dao;
    }

    @Override
    public List<Patient> getAllPatients(String query)
    {
        List<Patient> result = patientDao.findAll();
        return result.stream()
                .filter(patient -> patient.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}