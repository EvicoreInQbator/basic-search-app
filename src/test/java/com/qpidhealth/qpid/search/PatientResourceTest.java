package com.qpidhealth.qpid.search;

import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.repository.PatientDao;
import com.qpidhealth.qpid.search.rest.PatientResource;
import com.qpidhealth.qpid.search.services.PatientService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PatientResourceTest {

    private PatientService service;

    @Before
    public void setUp() {
        service = mock(PatientService.class);
        Patient p1 = new Patient(100000L, "Jhon Rambo");
        p1.add(new PatientDocs("Patient Note", "1/12/2008", "Jhon_1"));
        p1.add(new PatientDocs("Analisys", "6/20/2010", "Jhon_2"));
        List<Patient> results = new ArrayList<>();
        results.add(p1);
        when(service.getAllPatients("Jhon")).thenReturn(results);
    }

    @Test
    public void resourceReturnOK(){

        PatientResource resource = new PatientResource(service);
        Response response = resource.searchPatients("Jhon");

        assert(response != null);
        assertEquals(200, response.getStatus());
        verify(service,times(1)).getAllPatients("Jhon");
    }
    @Test
    public void resourceReturnBadRequest(){

        PatientResource resource = new PatientResource(service);
        Response response = resource.searchPatients(null);

        assert(response != null);
        assertEquals(400, response.getStatus());
        verifyNoMoreInteractions(service);
    }
}
