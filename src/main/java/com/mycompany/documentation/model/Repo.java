package com.mycompany.documentation.model;

import java.util.ArrayList;

/**
 *
 * @author lsimon
 */
public class Repo {
    
    private String name;
    private ArrayList<String> topics;
    private String description;
    private String url;
    private String owner;
    private String[] contributors;
    private String readme;
    private String startCursor;
    private String endCursor;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public Repo() {
    }

    public Repo(String name, ArrayList<String> topics, String description, String url, String owner, String[] contributors, String readme, String startCursor, String endCursor, boolean hasNextPage, boolean hasPreviousPage) {
        this.name = name;
        this.topics = topics;
        this.description = description;
        this.url = url;
        this.owner = owner;
        this.contributors = contributors;
        this.readme = readme;
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public Repo(String name, ArrayList<String> topics, String description, String url, String owner, String[] contributors, String readme) {
        this.name = name;
        this.topics = topics;
        this.description = description;
        this.url = url;
        this.owner = owner;
        this.contributors = contributors;
        this.readme = readme;
    }

    public Repo(String name, ArrayList<String> topics, String url, String owner, String readme, String startCursor, String endCursor, boolean hasNextPage, boolean hasPreviousPage) {
        this.name = name;
        this.topics = topics;
        this.url = url;
        this.owner = owner;
        this.readme = readme;
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }
    
    public Repo(String name, ArrayList<String> topics, String description, String url, String owner, String readme, String startCursor, String endCursor, boolean hasNextPage, boolean hasPreviousPage) {
        this.name = name;
        this.topics = topics;
        this.description = description;
        this.url = url;
        this.owner = owner;
        this.readme = readme;
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String[] getContributors() {
        return contributors;
    }

    public void setContributors(String[] contributors) {
        this.contributors = contributors;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getStartCursor() {
        return startCursor;
    }

    public void setStartCursor(String startCursor) {
        this.startCursor = startCursor;
    }

    public String getEndCursor() {
        return endCursor;
    }

    public void setEndCursor(String endCursor) {
        this.endCursor = endCursor;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
}
