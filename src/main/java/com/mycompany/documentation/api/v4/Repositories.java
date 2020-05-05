package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.ReposQuery;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/repositories")
@Produces(MediaType.APPLICATION_JSON)
public class Repositories {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReposWIthTopic(@DefaultValue("") @QueryParam("t") String[] topics,
            @DefaultValue("") @QueryParam("c") String c,
            @DefaultValue("") @QueryParam("f") String f,
            @DefaultValue("") @QueryParam("r") String r) {

        ReposQuery reposQueryClass = new ReposQuery();

        String res;

        if (r.isEmpty()) {
            //System.out.println("normal");
            res = reposQueryClass.getReposWithTopic(topics);
        } else {
            //System.out.println("reverse");
            //res = reposQueryClass.getReposWithoutTopic(topics);
            res = "";
        }

        //System.out.println(r);
        return res;
    }
}
