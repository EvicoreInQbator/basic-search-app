package com.qpidhealth.qpid.search;


import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.repository.PatientDao;
import com.qpidhealth.qpid.search.services.PatientService;
import com.qpidhealth.qpid.search.services.PatientServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientServiceTest {
    private PatientDao dao;

    @Before
    public void setUp() {

        dao = mock(PatientDao.class);
        Patient p1 = new Patient(100000L, "Jhon Rambo");
        p1.add(new PatientDocs("Patient Note", "1/12/2008", "Jhon_1"));
        p1.add(new PatientDocs("Analisys", "6/20/2010", "Jhon_2"));

        Patient p2 = new Patient(200001L, "Timoty Hill");
        p2.add(new PatientDocs("Clinical Note", "4/6/2010", "Timoty_1"));
        p2.add(new PatientDocs("Summary", "1/21/2015", "Timoty_2"));
        List<Patient> results = new ArrayList<>();
        results.add(p1);
        results.add(p2);
        when(dao.findAll()).thenReturn(results);
    }

    @Test
    public void serviceReturnListOfPatients() {
        PatientService service = new PatientServiceImpl(dao);
        assert (service.getAllPatients("JHON").size() == 1);
        assert (service.getAllPatients("jhon").size() == 1);
        assert (service.getAllPatients("PAT").size() == 0);
        assert (service.getAllPatients("").size() == 2);

    }
}