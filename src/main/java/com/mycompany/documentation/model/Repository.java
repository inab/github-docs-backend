package com.mycompany.documentation.model;

import java.util.ArrayList;

/**
 *
 * @author lsimon
 */
public class Repository {

    private String id;
    private String name;
    private ArrayList<String> topics;
    private String description;
    private String url;
    private ArrayList<String> languages;
    private String license;
    private String readme;
    private String startCursor;
    private String endCursor;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public Repository() {
    }

    public Repository(String id, String name, ArrayList<String> topics, String description, String url, ArrayList<String> languages, String license, String readme, String startCursor, String endCursor, boolean hasNextPage, boolean hasPreviousPage) {
        this.id = id;
        this.name = name;
        this.topics = topics;
        this.description = description;
        this.url = url;
        this.languages = languages;
        this.license = license;
        this.readme = readme;
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public Repository(String name, ArrayList<String> topics, String description, String url, String startCursor, String endCursor, boolean hasNextPage, boolean hasPreviousPage) {
        this.name = name;
        this.topics = topics;
        this.description = description;
        this.url = url;
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
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
