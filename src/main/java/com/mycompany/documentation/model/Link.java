package com.mycompany.documentation.model;

import java.util.ArrayList;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public class Link {

    private String url;
    private String name;
    private ArrayList<Topic> topics;

    public Link() {
    }

    public Link(String link, String name, ArrayList<Topic> topics) {
        this.url = link;
        this.name = name;
        this.topics = topics;
    }
    
    public Link(String link, String name) {
        this.url = link;
        this.name = name;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Link{link=").append(url);
        sb.append(", name=").append(name);
        sb.append(", topics=").append(topics);
        sb.append('}');
        return sb.toString();
    }
}
