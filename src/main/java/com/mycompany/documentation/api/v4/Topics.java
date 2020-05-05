package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.TopicsQuery;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class Topics {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList getTopics() {
        TopicsQuery topicsQueryClass = new TopicsQuery();

        return topicsQueryClass.getTopics();
    }
}
