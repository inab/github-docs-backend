package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.InabRepoQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/repository")
@Produces(MediaType.APPLICATION_JSON)
public class InabRepo {
    
    @GET
    @Path("/{repoName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInabRepo(@PathParam("repoName") String repoName) {
        InabRepoQuery inabRepoQueryClass = new InabRepoQuery();

        return inabRepoQueryClass.getInabRepo(repoName);
    }
}
