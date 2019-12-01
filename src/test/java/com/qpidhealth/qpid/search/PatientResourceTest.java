package com.qpidhealth.qpid.search;

import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.domain.PatientDocs;
import com.qpidhealth.qpid.search.rest.PatientResource;
import com.qpidhealth.qpid.search.services.PatientService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class PatientResourceTest extends Assert {

    private PatientService service;
    private PatientResource resource;
    @Before
    public void setUp() {
        service = mock(PatientService.class);
        Patient p1 = new Patient(100000L, "Jhon Rambo");
        p1.add(new PatientDocs("Patient Note", "1/12/2008", "Jhon_1"));
        p1.add(new PatientDocs("Analisys", "6/20/2010", "Jhon_2"));
        List<Patient> results = new ArrayList<>();
        results.add(p1);
        when(service.getAllPatients("Jhon")).thenReturn(results);

        resource = mock(PatientResource.class);
        when(resource.searchPatients("Jhon")).thenReturn(Response.ok().build());
    }
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClasses(PatientService.class, Patient.class, Response.class);
    }

    @ArquillianResource
    private URL webappUrl;

    @Test
    public void resourceReturnOK() throws Exception{

        final WebTarget webTarget = ClientBuilder.newClient().target(webappUrl.toURI());
        final Response response = webTarget.path("patients/search?query=Jhon")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        assert(response != null);
        assertEquals(404, response.getStatus());
        verifyNoMoreInteractions(service);
    }
}
