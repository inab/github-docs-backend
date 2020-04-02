package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.*;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author lsimon
 */
public class TopicQueries {

    public TopicQueries() {
    }

    public String getJsonObj(JSONObject jsonObj) {
        JsonObj jsonObjClass = new JsonObj();
        return jsonObjClass.getJsonObj(jsonObj);
    }

    public String getTopicsFromAllRepos(String login) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: 100){\n"
                + "      totalCount\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: 100){\n"
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
                + "    }\n"
                + "  }\n"
                + "}");

        return getJsonObj(jsonObj);
    }

    public String getTopicsFromARepo(String repoName, String owner) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repository(name: \"" + repoName + "\", owner: \"" + owner + "\") {\n"
                + "    name\n"
                + "    repositoryTopics(first: 100){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          topic{\n"
                + "	       name\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return getJsonObj(jsonObj);
    }

    public int getNumRepos(String login) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: 10) {\n"
                + "      totalCount\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = getJsonObj(jsonObj);

        JSONObject json = new JSONObject(jsonString);

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get number of repos
        int numRepos = repositories.getInt("totalCount");

        return numRepos;
    }

    public int getNumTopics(String login) {
        int numRepos = getNumRepos(login);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: " + numRepos + ") {\n"
                + "      edges {\n"
                + "        node {\n"
                + "          repositoryTopics(first: 10) {\n"
                + "            totalCount\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = getJsonObj(jsonObj);

        JSONObject json = new JSONObject(jsonString);

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        int numTopics = 0;
        JSONArray reposArray = repositories.getJSONArray("edges");
        for (int i = 0; i < numRepos; i++) {
            //get number of topics 
            numTopics = reposArray.getJSONObject(i).getJSONObject("node").getJSONObject("repositoryTopics").getInt("totalCount");
        }

        return numTopics;
    }

    public String getReposWithTopic(String login, String topic) {
        int numRepos = getNumRepos(login);
        int numTopics = getNumTopics(login);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      totalCount\n"
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
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = getJsonObj(jsonObj);

        JSONObject json = new JSONObject(jsonString);

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get repo names and topics
        String repoName, topicName, topicUrl, topicDescription;
        ArrayList reposArrayList = new ArrayList<Repo>();
        JSONArray reposArray = repositories.getJSONArray("edges");
        for (int i = 0; i < numRepos; i++) {
            //get name
            repoName = reposArray.getJSONObject(i).getJSONObject("node").getString("name");

            //get topics 
            ArrayList topicsArrayList = new ArrayList<Topic>();
            JSONArray topicsArray = reposArray.getJSONObject(i).getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");
            for (int j = 0; j < topicsArray.length(); j++) {
                topicName = topicsArray.getJSONObject(j).getJSONObject("node").getJSONObject("topic").getString("name");
                if (topics.get(j).getName().equalsIgnoreCase(topicName)) {
                    topicUrl = topics.get(j).getUrl();
                    topicDescription = topics.get(j).getDescription();

                    //add topic name, url and description to topic list
                    topicsArrayList.add(new Topic(topicName, topicUrl, topicDescription));
                } else {
                    //add only topic name to topic list
                    topicsArrayList.add(new Topic(topicName));
                }

                if (topicName.equalsIgnoreCase(topic)) {
                    //add repo name and topics to repo list
                    reposArrayList.add(new Repo(repoName, topicsArrayList));
                }
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }
}

//Json query with pagination
/*jsonObj.put("query", "query{\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: 10) {\n"
                + "      totalCount\n"
                + "      edges {\n"
                + "        node {\n"
                + "          name\n"
                + "          repositoryTopics(first: 100) {\n"
                + "            edges {\n"
                + "              node {\n"
                + "                topic {\n"
                + "                  name\n"
                + "                }\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "      pageInfo {\n"
                + "        endCursor\n"
                + "        hasNextPage\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");*/
