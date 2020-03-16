/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.HtmlREADMEfromURL;
import java.net.MalformedURLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */


//This should be in use and should remove the InabOnlyReadme.java
// This implies Client side changes
@Path("/html")
@Produces(MediaType.TEXT_HTML)
public class AllReadme extends HtmlREADMEfromURL {
    
    /**
     * by using html/owner/id you can retrive any readme.md from any repository on github
     * you do NOT need this function for the documentation hub.
     * @param owner would be inab
     * @param id would be the name of the repo uptime-chart-oeb
     * @return calls main function to retrive the document
     * @throws MalformedURLException
     */
    @GET
    @Path("/{owner}/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String repoByAutorAndId(
        @PathParam("owner") String owner,
        @PathParam("id") String id
    ) throws MalformedURLException{
        return getREADMEfromURL(id,owner);
    }
    
}
