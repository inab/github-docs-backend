package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.TopicQueries;
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
public class Topics {
    
    /**
     * It retrieves the topics from all repos from an owner
     * @param login
     * @return calls the function to retrieve the topics
     */
    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopicsFromAllRepos(@PathParam("login") String login) {
        TopicQueries tq = new TopicQueries();

        return tq.getTopicsFromAllRepos(login);
    }
    
    /**
     * It retrieves the topics from a determined repo from an owner
     * @param repoName
     * @param owner
     * @return calls the function to retrieve the topics
     */
    @GET
    @Path("/{repoName}/{owner}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopicsFromARepo(@PathParam("repoName") String repoName, @PathParam("owner") String owner) {
        TopicQueries tq = new TopicQueries();

        return tq.getTopicsFromARepo(repoName, owner);
    }
    
    /**
     * It retrieves the repos from an owner which contain a determined topic
     * @param login
     * @param topic
     * @return 
     */
    @GET
    @Path("/{login}/{topic}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReposWithTopic(@PathParam("login") String login, @PathParam("topic") String topic) {
        TopicQueries tq = new TopicQueries();

        return tq.getReposWithTopic(login, topic);
    }
}
