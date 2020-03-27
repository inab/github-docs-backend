package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.ReadmeQueryV4;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ReadmeV4 {

    @GET
    @Path("/{repoName}/{owner}/readme")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReadme(@PathParam("repoName") String repoName, @PathParam("owner") String owner) {
        ReadmeQueryV4 rq = new ReadmeQueryV4();

        return rq.getReadme(repoName, owner);
    }
    
    @GET
    @Path("/{login}/readme")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllReadmes(@PathParam("login") String login) {
        ReadmeQueryV4 rq = new ReadmeQueryV4();

        return rq.getAllReadmes(login);
    }
}
