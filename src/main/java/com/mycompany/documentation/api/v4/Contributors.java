package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.ContributorsQuery;
import com.mycompany.documentation.model.Contributor;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;

/**
 *
 * @author lsimon
 */
@Path("/contributors")
@Produces(MediaType.APPLICATION_JSON)
public class Contributors {

    @GET
    @Path("/{repo}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getContributors(@PathParam("repo") String repo) throws IOException {

        ContributorsQuery contributorsQueryClass = new ContributorsQuery();

        return contributorsQueryClass.getContributors(repo);
    }
}
