package com.qpidhealth.qpid.search.rest;

import com.qpidhealth.qpid.search.domain.Patient;
import com.qpidhealth.qpid.search.repository.PatientDao;
import com.qpidhealth.qpid.search.services.PatientService;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ejb.LockType.READ;

@Path("/patients")
@Singleton
@Lock(READ)
public class PatientResource {

    private PatientService patientService;

    @Inject
    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchPatients(@QueryParam("query") String query){
        if(query != null) {
            return Response.ok(patientService.getAllPatients(query)).build();
        }
        return Response.status(400).build();
    }
}
