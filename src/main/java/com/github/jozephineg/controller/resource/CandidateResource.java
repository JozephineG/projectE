package com.github.jozephineg.controller.resource;

import com.github.jozephineg.controller.service.CandidateSearchLogic;
import com.github.jozephineg.model.data.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("candidates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CandidateResource {
    private CandidateSearchLogic candidateSearchLogic;

    @Autowired
    public CandidateResource(CandidateSearchLogic candidateSearchLogic) {
        this.candidateSearchLogic = candidateSearchLogic;
    }

    @GET
    public List<Candidate> searchForCandidate(@QueryParam("constituency") String constituency,
                                              @QueryParam("firstName") String firstName,
                                              @QueryParam("lastName") String lastName) {
        if(!(constituency == null) && !constituency.isEmpty()) {
            return candidateSearchLogic.findByConstituency(constituency);
        }

        if(!(firstName == null) && !firstName.isEmpty() && !(lastName == null) && !lastName.isEmpty()) {
            return candidateSearchLogic.findByFirstNameAndLastName(firstName, lastName);
        }

        throw new BadRequestException("You need to specify either firstName, lastName or constituency");
    }


}
