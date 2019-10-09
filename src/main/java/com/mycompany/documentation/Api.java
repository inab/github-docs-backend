/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

import static com.mycompany.documentation.Constants.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;


/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
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
                case "oebRepos":
                    list = Constants.oebRepos;
                    break;
                case "widgetGalleryRepos":
                    list = Constants.widgetGalleryRepos;
                    break;
                default:
                    break;
            }
        }
        return list;
    };
    
    
    /**
     * Constructs the github url that has to be queried
     * @param i the name of the repository
     * @return String url
     * @throws MalformedURLException 
     */
    public String constructUrl(String i) throws MalformedURLException{
        return githubApi+"/"+owner+"/"+i+"/"+readmeEndpoint;
    }
    
    /**
     * Main function of the program, queries the url, decodes the content 
     * which is encoded en base64 and then passes it to MdHtmlParser be 
     * converted to HTML finally adds the link to the original github README.md
     * page and returns in HTML format to the web
     * @param id
     * @return HTML Format
     * @throws IOException 
     */
    @GET
    @Path("/{repolist}/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String repoById(
            @PathParam("repolist") String repolist,
            @PathParam("id") String id) throws IOException{
            String urlString = this.constructUrl(id);
            URL url = new URL(urlString);
            try (BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()))){
                JsonReader reader = Json.createReader(read);

                JsonObject object = reader.readObject();
                String content = object.getJsonString("content").getString();
                String htmlUrl = object.getJsonString("html_url").getString();

                byte[] contentByte = Base64.decodeBase64(content);

                String s = new String(contentByte);

                MdHtmlParser mdhp = new MdHtmlParser();

                String docs = mdhp.mdtoHtml(s, id);

                Element aGithubLink = new Element(Tag.valueOf("a"),"")
                        .attr("href", htmlUrl)
                        .addClass("readme")
                        .text("README.md");

                Element divBody = new Element(Tag.valueOf("div"),"")
                        .appendChild(aGithubLink)
                        .append(docs);

                return divBody.toString();
            } catch (IOException e){
                System.out.println(e);
            }        
        return "ups sorry something somewhere broke. Our trained monkeys have"
                + " been informed and are working on it. Please try later !";
    }
    
    
}

