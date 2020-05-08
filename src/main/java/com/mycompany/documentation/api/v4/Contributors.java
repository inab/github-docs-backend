package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.ReposQuery;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/repos")
@Produces(MediaType.APPLICATION_JSON)
public class Contributors {

    @GET
    @Path("/{owner}/{repo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReposWIthTopic(
            //@PathParam("owner") String owner,
            @PathParam("repo") String repo) throws IOException {

        ReposQuery reposQueryClass = new ReposQuery();

        return reposQueryClass.getContributors(repo);
    }
}
