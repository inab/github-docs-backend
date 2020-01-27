/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
@Path("/")
public class Api {
    
    /**
     * Returns the list of repositories for the menu of the documentation page
     * @param repolist the name of the repolist eg: oeb, wg .. etc
     * @return list of repositories
     */
    @GET
    @Path("/{repolist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> repoListToHtml(@PathParam("repolist") String repolist){
        Map<String,String> list = Constants.oebRepos;
        if(!repolist.isEmpty()){
            switch (repolist){
                case "oeb":
                    list = Constants.oebRepos;
                    break;
                case "wg":
                    list = Constants.widgetGalleryRepos;
                    break;
                case "vre":
                    list = Constants.vre;
                    break;
                default:
                    break;
            }
        }
        return list;
    };
    
    /**
     * Returns list of projects for the documentation hub.
     * 
     */
    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> getProjects(){
        return Constants.projects;
    };
    

}

