package com.qpidhealth.qpid.search.services;

import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.repository.PatientDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PatientServiceImpl implements PatientService{
    @Inject
    private PatientDao patientDao;

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }
}
