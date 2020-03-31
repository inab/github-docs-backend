package com.mycompany.documentation.model;

import java.util.ArrayList;

/**
 *
 * @author lsimon
 */
public class Repo {
    private String name;
    private ArrayList<Topic> topics;
    private int totalCount;

    public Repo() {
    }

    public Repo(String name, ArrayList<Topic> topics, int totalCount) {
        this.name = name;
        this.topics = topics;
        this.totalCount = totalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
