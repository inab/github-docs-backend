package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repo;
import java.util.ArrayList;
import java.util.Arrays;
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
    NumTopics numTopicsClass = new NumTopics();
    JsonObj jsonObjClass = new JsonObj();

    public String getReposWithTopic(String[] topic) {
        int numRepos = numReposClass.getNumRepos();
        int numTopics = numTopicsClass.getNumTopics();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: " + numTopics + "){\n"
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
                + "          owner {\n"
                + "            login\n"
                + "          }\n"
                + "          object(expression: \"master:README.md\") {\n"
                + "            ... on Blob {\n"
                + "              text\n"
                + "            }\n"
                + "          }\n"
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
        String repoName, repoDescription, repoUrl, repoOwner, repoReadme, topicName;
        ArrayList<Repo> reposArrayList = new ArrayList<>();
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

            //get array of topics from repo 
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

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

            //compare the two lists to check if all the topics defined by user are present in topic list from repo
            if (tmp.containsAll(Arrays.asList(topic))) {
                //get repo name
                repoName = repoObj.getJSONObject("node").getString("name");

                //get repo description
                /*if (repoObj.getJSONObject("node").get("description") != null) {
                    repoDescription = repoObj.getJSONObject("node").getString("description");
                } else {
                    repoDescription = "";
                }*/
                                
                //get repo url
                repoUrl = repoObj.getJSONObject("node").getString("url");

                //get repo owner
                repoOwner = repoObj.getJSONObject("node").getJSONObject("owner").getString("login");

                //get repo readme
                repoReadme = repoObj.getJSONObject("node").getJSONObject("object").getString("text");

                //add topics to array of topics
                topicsArrayList = new ArrayList<>();
                for (String top : tmp) {
                    topicsArrayList.add(top);
                }

                //add name and array of topics to array of repos
                reposArrayList.add(new Repo(repoName, topicsArrayList, /*repoDescription, */repoUrl, repoOwner, repoReadme, startCursor, endCursor, hasNextPage, hasPreviousPage));
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }

    /*public String getReposWithoutTopic(String[] topic) {
        int numRepos = numReposClass.getNumRepos();
        int numTopics = numTopicsClass.getNumTopics();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: " + numTopics + "){\n"
                + "            edges{\n"
                + "              node{\n"
                + "                topic{\n"
                + "		     name\n"
                + "                }\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
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
        String repoName, topicName;
        ArrayList<Repo> reposArrayList = new ArrayList<>();
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

            //get array of topics from repo 
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

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

            //compare the two lists to check if all the topics defined by user are present in topic list from repo
            if (!tmp.containsAll(Arrays.asList(topic))) {
                //get repo name
                repoName = repoObj.getJSONObject("node").getString("name");

                //add topics to array of topics
                topicsArrayList = new ArrayList<>();
                for (String top : tmp) {
                    topicsArrayList.add(top);
                }

                //add name and array of topics to array of repos
                reposArrayList.add(new Repo(repoName, topicsArrayList, startCursor, endCursor, hasNextPage, hasPreviousPage));
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }*/
}
