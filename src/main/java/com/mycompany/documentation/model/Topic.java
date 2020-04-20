package com.mycompany.documentation.model;

/**
 *
 * @author lsimon
 */
public class Topic {
    
    private String name;
    private String url;
    private String description;

    public Topic() {
    }

    public Topic(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Topic{name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
    }
}
