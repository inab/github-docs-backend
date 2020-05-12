package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.AllInabReposQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/repositories")
@Produces(MediaType.APPLICATION_JSON)
public class AllInabRepos {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllInabRepos() {
        AllInabReposQuery allInabReposQueryClass = new AllInabReposQuery();

        return allInabReposQueryClass.getAllInabRepos();
    }
}
