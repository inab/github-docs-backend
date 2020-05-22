package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.ContributorsQuery;
import com.mycompany.documentation.api.logic.v4.RepoQuery;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

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
    public String getRepo(@PathParam("repoName") String repoName) throws IOException {
        RepoQuery repoQueryClass = new RepoQuery();
        ContributorsQuery contsQueryClass = new ContributorsQuery();

        JSONObject repoObj = repoQueryClass.getRepo(repoName);
        JSONObject conts = contsQueryClass.getContributors(repoName);
        repoObj.append("contributors", conts);

        return repoObj.toString();
    }
}
