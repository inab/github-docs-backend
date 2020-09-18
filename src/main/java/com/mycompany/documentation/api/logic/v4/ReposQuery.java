package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class ReposQuery {

    public ReposQuery() {
    }

    NumRepos numReposClass = new NumRepos();
    JsonObj jsonObjClass = new JsonObj();

    public String getReposWithTopic(ArrayList<String> topics, String typeSelect) {
        int numRepos = numReposClass.getNumRepos();
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + LOGIN + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: 10){\n"
                + "            edges{\n"
                + "              node{\n"
                + "                topic{\n"
                + "		     name\n"
                + "                }\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "          description\n"
                + "          url\n"
                + "        }\n"
                + "      }\n"
                + "      pageInfo {\n"
                + "        startCursor\n"
                + "        endCursor\n"
                + "        hasNextPage\n"
                + "        hasPreviousPage\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //vars repos and topics
        String repoName, repoDescription, repoUrl, topicName;
        Set<Repository> reposArrayList = new HashSet<>();
        ArrayList<String> topicsArrayList;

        //vars pagination
        String startCursor, endCursor;
        Boolean hasNextPage, hasPreviousPage;

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get pageInfo
        JSONObject pageInfo = repositories.getJSONObject("pageInfo");

        //get startCursor, endCursor, hasNextPage, hasPreviousPage
        startCursor = pageInfo.getString("startCursor");
        endCursor = pageInfo.getString("endCursor");
        hasNextPage = pageInfo.getBoolean("hasNextPage");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");

        //get array of repos returned by github
        JSONArray reposArray = repositories.getJSONArray("edges");

        //iterate over array of repos, find if repositoryTopics contains topics defined by user
        for (Object o : reposArray) {
            JSONObject repoObj = new JSONObject(o.toString());

            //get repo name, description and url
            repoName = repoObj.getJSONObject("node").getString("name");
            if (repoObj.getJSONObject("node").isNull("description")) {
                repoDescription = "";
            } else {
                repoDescription = repoObj.getJSONObject("node").getString("description");
            }
            repoUrl = repoObj.getJSONObject("node").getString("url");

            //get array of topics from repo 
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

            if (topics.isEmpty()) {
                topicsArrayList = new ArrayList<>();
                for (Object oo : topicsArray) {
                    JSONObject topicObj = new JSONObject(oo.toString());

                    //get topic name
                    topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
                    //add topic name to array of topics
                    topicsArrayList.add(topicName);
                }

                //add all repo attributes to array of repos
                reposArrayList.add(new Repository(repoName, topicsArrayList, repoDescription, repoUrl, startCursor, endCursor, hasPreviousPage, hasNextPage));
            } else {
                //create temp list
                ArrayList<String> tmp = new ArrayList<>();
                //iterate over array of topics and add them to the tmp list
                //this is done so we can compare 2 lists: topics defined by user against topics from repo
                for (Object oo : topicsArray) {
                    JSONObject topicObj = new JSONObject(oo.toString());

                    //get topic name
                    topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
                    //add topic name to array of topics
                    tmp.add(topicName);
                }

                if ("inclusive".equals(typeSelect)) {
                    ArrayList<String> openebench = new ArrayList<>();
                    openebench.add("openebench");
                    boolean repoExist = false;
                    for (int t = 0; t < topics.size(); t++) {
                        if (tmp.contains(topics.get(t)) && topics != openebench) {
                            topicsArrayList = new ArrayList<>();
                            for (String top : tmp) {
                                topicsArrayList.add(top);
                            }
        
                            if (!reposArrayList.contains(new Repository(repoName, topicsArrayList, repoDescription, repoUrl, startCursor, endCursor, hasPreviousPage, hasNextPage))) {
                                //get an iterator
                                Iterator<Repository> iterator = reposArrayList.iterator();
                                for (Repository repo : reposArrayList) {
                                    if (repo.getUrl().equals(repoUrl)) {
                                        repoExist = true;
                                    }
                                }
                                
                                if (!repoExist) {
                                    reposArrayList.add(new Repository(repoName, topicsArrayList, repoDescription, repoUrl, startCursor, endCursor, hasPreviousPage, hasNextPage)); 
                                }
                                
                            }
                            
                        }
                    }
                    
                } else if ("exclusive".equals(typeSelect)) {
                    //compare the two lists to check if all the topics defined by user are present in topic list from repo
                    if (tmp.containsAll(topics)) {
                        //add topics to array of topics
                        topicsArrayList = new ArrayList<>();
                        for (String top : tmp) {
                            topicsArrayList.add(top);
                        }

                        //add all repo attributes to array of repos
                        reposArrayList.add(new Repository(repoName, topicsArrayList, repoDescription, repoUrl, startCursor, endCursor, hasPreviousPage, hasNextPage));
                    }
                }
                
            }
        }

        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }
}
