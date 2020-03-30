/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import static com.mycompany.documentation.api.logic.Constants.*;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public class MdHtmlParser {

    public MdHtmlParser() {
    }

    /**
     * This function uses the commonmark library to convert md to HTML
     *
     * @param s input in md format
     * @param repo repo name to parse urls and images
     * @return HTML String
     * @throws MalformedURLException
     * @throws IOException
     */
    public String mdtoHtml(String s, final String repo) throws MalformedURLException, IOException {
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().build();
        Node document = parser.parse(s);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new ParseUrls(repo);
                    }
                })
                .build();
        return renderer.render(document);
    }

    class ParseUrls implements AttributeProvider {

        private final String repo;

        private ParseUrls(String repo) {
            this.repo = repo;
        }

        /**
         * parses urls for internal links and images on github, creates
         * redirects to the original content
         *
         * @param node
         * @param tagName
         * @param attributes
         */
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Image) {
                if (!((Image) node).getDestination().contains("http")) {
                    attributes.put("src", githubRaw + "/" + owner + "/" + repo + "/master/" + ((Image) node).getDestination());
                }
            }
            if (node instanceof Link) {
                if (!((Link) node).getDestination().contains("http")) {
                    attributes.put("href", github + "/" + owner + "/" + repo + "/tree/master/" + ((Link) node).getDestination());
                }
            }
        }
    }
}
