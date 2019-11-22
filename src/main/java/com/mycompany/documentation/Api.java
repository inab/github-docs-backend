/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

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
    public String[] repoListToHtml(@PathParam("repolist") String repolist){
        String [] list = Constants.oebRepos;
        if(!repolist.isEmpty()){
            switch (repolist){
                case "oeb":
                    list = Constants.oebRepos;
                    break;
                case "wg":
                    list = Constants.widgetGalleryRepos;
                    break;
                default:
                    break;
            }
        }
        return list;
    };
    

}

