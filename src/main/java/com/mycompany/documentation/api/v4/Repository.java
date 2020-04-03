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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataFromARepoWithoutParams() {
        RepoQuery repoQueryClass = new RepoQuery();

        return repoQueryClass.getDataFromARepoWithoutParams();
    }
    
    /**
     * It retrieves the topics and readme from a determined repo from an owner
     * @param repoName
     * @return calls the function to retrieve the topics
     */
    @GET
    @Path("/{repoName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataFromARepo(@PathParam("repoName") String repoName) {
        RepoQuery repoQueryClass = new RepoQuery();

        return repoQueryClass.getDataFromARepo(repoName);
    }
}
