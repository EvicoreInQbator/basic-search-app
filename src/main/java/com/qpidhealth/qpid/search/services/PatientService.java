package com.qpidhealth.qpid.search.services;


import com.qpidhealth.qpid.search.domain.Patient;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PatientService {
    public List<Patient> getAllPatients();
}
