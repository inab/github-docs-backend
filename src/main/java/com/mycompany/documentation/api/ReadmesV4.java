package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.ReadmeQueriesV4;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/readmes")
@Produces(MediaType.APPLICATION_JSON)
public class ReadmesV4 {

    /**
     * It retrieves the readmes from all repos from an owner
     * @param login
     * @return calls the function to retrieve the readmes
     */
    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReadmesFromAllRepos(@PathParam("login") String login) {
        ReadmeQueriesV4 rq = new ReadmeQueriesV4();

        return rq.getReadmesFromAllRepos(login);
    }
    
    /**
     * It retrieves the readme from a determined repo from an owner
     * @param repoName
     * @param owner
     * @return calls the function to retrieve the readme
     */
    @GET
    @Path("/{repoName}/{owner}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReadmeFromARepo(@PathParam("repoName") String repoName, @PathParam("owner") String owner) {
        ReadmeQueriesV4 rq = new ReadmeQueriesV4();

        return rq.getReadmeFromARepo(repoName, owner);
    }
}
