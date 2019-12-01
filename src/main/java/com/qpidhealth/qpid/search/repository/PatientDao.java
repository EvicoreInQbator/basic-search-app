package com.qpidhealth.qpid.search.repository;

import com.qpidhealth.qpid.search.domain.Patient;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PatientDao {
    List<Patient> findAll();
}
