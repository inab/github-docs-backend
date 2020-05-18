package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.AllReposQuery;
import static com.mycompany.documentation.api.logic.v4.Constants.project;
import com.mycompany.documentation.api.logic.v4.ContributorsQuery;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public String getAllReposWithTopic(@QueryParam("t") ArrayList<String> topics) throws IOException {

        AllReposQuery allRposQueryClass = new AllReposQuery();
        ContributorsQuery contsQueryClass = new ContributorsQuery();
        ArrayList<String> topicstofilter = new ArrayList<>();

        if (!topics.isEmpty()) {
            for (String s : topics) {
                topicstofilter.add(s);
            }
        }
        topicstofilter.add(project);

        JSONArray repos = new JSONArray();
        JSONArray reposArray = allRposQueryClass.getAllReposWithTopic(topicstofilter);

        for (Object o : reposArray) {
            JSONObject repoObj = new JSONObject(o.toString());

            String repoName = repoObj.getString("name");
            repoObj.append("contributors", contsQueryClass.getContributors(repoName));

            repos.put(repoObj);
        }

        return repos.toString();
    }
}
