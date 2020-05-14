package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.RepoQuery;
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
public class Repository {
    
    @GET
    @Path("/{repoName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRepo(@PathParam("repoName") String repoName) {
        RepoQuery repoQueryClass = new RepoQuery();

        return repoQueryClass.getRepo(repoName);
    }
}
