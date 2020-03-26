/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api.logic;

import com.mycompany.documentation.api.logic.MdHtmlParser;
import static com.mycompany.documentation.api.logic.Constants.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public abstract class HtmlREADMEfromURL {

    /**
     * Constructs the github url that has to be queried
     *
     * @param i the name of the repository
     * @return String url
     * @throws MalformedURLException
     */
    public String constructUrl(String i, String owner) throws MalformedURLException {
        return githubApi + "/" + owner + "/" + i + "/" + readmeEndpoint;
    }

    /**
     * Main function of the program, constructs and queries the url, decodes the
     * content which is encoded en base64 and then passes it to MdHtmlParser be
     * converted to HTML finally adds the link to the original github README.md
     * page and returns in HTML format to the web
     *
     * @param id
     * @param owner
     * @return HTML Format
     * @throws IOException
     */
    public String getREADMEfromURL(String id, String owner) throws MalformedURLException {
        String urlString = constructUrl(id, owner);
        URL url = new URL(urlString);

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setRequestProperty("Authorization",  "Bearer " +  Constants.TOKEN);

            BufferedReader read = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            JsonReader reader = Json.createReader(read);

            JsonObject object = reader.readObject();
            String content = object.getJsonString("content").getString();
            String htmlUrl = object.getJsonString("html_url").getString();

            byte[] contentByte = Base64.decodeBase64(content);

            String s = new String(contentByte);

            MdHtmlParser mdhp = new MdHtmlParser();

            String docs = mdhp.mdtoHtml(s, id);

            Element aGithubLink = new Element(Tag.valueOf("a"), "")
                    .attr("href", htmlUrl)
                    .addClass("readme")
                    .text("README.md");

            Element divBody = new Element(Tag.valueOf("div"), "")
                    .appendChild(aGithubLink)
                    .append(docs);

            return divBody.toString();
        } catch (IOException e) {
            System.out.println(e);
        }
        return "ups sorry something somewhere broke. Our trained monkeys have"
                + " been informed and are working on it. Please try later !";
    }

}
