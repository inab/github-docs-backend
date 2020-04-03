package com.mycompany.documentation.api.v4;

import com.mycompany.documentation.api.logic.v4.ALLTopicQueries;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lsimon
 */
@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class ALLTopics {
    
    /**
     * It retrieves the topics from all repos from an owner
     * @return calls the function to retrieve the topics
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopicsFromAllRepos() {
        ALLTopicQueries tq = new ALLTopicQueries();

        return tq.getTopicsFromAllRepos();
    }
    
    /**
     * It retrieves the topics from a determined repo from an owner
     * @param repoName
     * @return calls the function to retrieve the topics
     */
    @GET
    @Path("/{repoName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopicsFromARepo(@PathParam("repoName") String repoName) {
        ALLTopicQueries tq = new ALLTopicQueries();

        return tq.getTopicsFromARepo(repoName);
    }
    
    /**
     * It retrieves the repos from an owner which contain a determined topic
     * @param topic
     * @return calls the function to retrieve the repos
     */
    @GET
    @Path("/{topic}/topics")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReposWithTopic(@PathParam("topic") String topic) {
        ALLTopicQueries tq = new ALLTopicQueries();

        return tq.getReposWithTopic2(topic);
    }
}
