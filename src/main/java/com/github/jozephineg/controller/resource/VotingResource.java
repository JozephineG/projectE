package com.github.jozephineg.controller.resource;

import com.github.jozephineg.controller.service.VotingSearchLogic;
import com.github.jozephineg.model.data.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("votings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VotingResource {

    private VotingSearchLogic votingSearchLogic;

    @Autowired
    public VotingResource(VotingSearchLogic votingSearchLogic) {
        this.votingSearchLogic = votingSearchLogic;
    }

    @GET
    public List<Voting> searchForVoting(@QueryParam("constituency") String constituency,
                                        @QueryParam("votingId") String votingId) {
        if(!(constituency == null) && !constituency.isEmpty()) {
            return votingSearchLogic.findByConstituency(constituency);
        }

        if(!(votingId == null) && !votingId.isEmpty()) {
            return votingSearchLogic.findByVotingId(votingId);
        }

        throw new BadRequestException("You need to specify either votingId or constituency");
    }
}
