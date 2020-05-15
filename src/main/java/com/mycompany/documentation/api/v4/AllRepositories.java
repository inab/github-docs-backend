package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.AllReposQuery;
import static com.mycompany.documentation.api.logic.v4.Constants.project;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/repos")
@Produces(MediaType.APPLICATION_JSON)
public class AllRepositories {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllReposWithTopic(@QueryParam("t") ArrayList<String> topics) {

        AllReposQuery allRposQueryClass = new AllReposQuery();
        ArrayList<String> topicstofilter = new ArrayList<>();

        if (!topics.isEmpty()) {
            for (String s : topics) {
                topicstofilter.add(s);
            }
        }
        topicstofilter.add(project);
        return allRposQueryClass.getAllReposWithTopic(topicstofilter);
    }
}
