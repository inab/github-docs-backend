package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repo;
import com.mycompany.documentation.model.Topic;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class ReposQuery {

    public ReposQuery() {
    }

    JsonObj jsonObjClass = new JsonObj();
    NumRepos numReposClass = new NumRepos();
    int numRepos = numReposClass.getNumRepos();
    NumTopics numTopicsClass = new NumTopics();
    int numTopics = numTopicsClass.getNumTopics();

    public String getReposWithTopic(String[] topic) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
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
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);

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

                for (int k = 0; k < topic.length; k++) {
                    if (topicName.equalsIgnoreCase(topic[k])) {
                        //add repo name and topics to repo list
                        reposArrayList.add(new Repo(repoName, topicsArrayList));
                    }
                }
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }
}
