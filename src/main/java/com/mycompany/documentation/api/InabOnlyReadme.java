/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api;

import com.mycompany.documentation.api.logic.HtmlREADMEfromURL;
import static com.mycompany.documentation.api.logic.Constants.*;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
@Path("/gh")
@Produces(MediaType.TEXT_HTML)
public class InabOnlyReadme extends HtmlREADMEfromURL {

    /**
     * This should be deleted and only use AllReadme.java This is used to retive
     * the readms from all inab repositories since we already know the owner we
     * only need to pass in the repo name
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String repoById(
            //@PathParam("repolist") String repolist,
            @PathParam("id") String id) throws IOException {
        return getREADMEfromURL(id, owner);
    }
}
