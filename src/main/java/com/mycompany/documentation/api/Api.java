/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.Constants;
import com.mycompany.documentation.model.Link;
import java.util.ArrayList;
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
     *
     * @param repolist the name of the repolist eg: oeb, wg .. etc
     * @return list of repositories
     */
    @GET
    @Path("/{repolist}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Link> repoListToHtml(@PathParam("repolist") String repolist) {
        ArrayList<Link> list = Constants.oebRepos;
        if (!repolist.isEmpty()) {
            switch (repolist) {
                case "oeb":
                    list = Constants.oebRepos;
                    break;
                case "wg":
                    list = Constants.wgRepos;
                    break;
                case "vre":
                    list = Constants.vreRepos;
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    /**
     * Returns list of projects for the documentation hub.
     *
     * @return
     */
    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Link> getProjects() {
        return Constants.projects;
    }
}
