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
            //@DefaultValue("") @QueryParam("cursor") String cursor,
            //@DefaultValue("") @QueryParam("first") String first,
            @DefaultValue("") @QueryParam("reverse") String reverse) {
        ReposQuery reposQueryClass = new ReposQuery();
        
        /*if (cursor == "") {
            System.out.println("vuelta 1");
        } else {
            System.out.println(cursor);
        }*/
        
        Boolean r = false;
        if (reverse != "") {
            r = true;
            
        } 
        if (reverse == "") {
            r = false;
        }
        
        if (!r) {
            return reposQueryClass.getReposWithTopic(topics);
        } else {
            return reposQueryClass.getReposWithoutTopic(topics);
        }
    }
}
