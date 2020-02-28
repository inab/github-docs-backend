/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api;

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

@Path("/html")
@Produces(MediaType.TEXT_HTML)
public class AllReadme extends HtmlREADMEfromURL {
    
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
