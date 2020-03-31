package com.mycompany.documentation.api.logic.v4;

import com.mycompany.documentation.api.logic.v4.JsonObj;
import com.mycompany.documentation.model.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;

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
                + "    repositories(first:4){\n"
                + "      totalCount\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first:4){\n"
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
                + "    repositoryTopics(first:4){\n"
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

    public String getReposWithTopic(String login, String topic) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first:4){\n"
                + "      totalCount\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first:4){\n"
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

        // Remove keys we dont need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");
        
        //number of repositories
        Integer numRepos = repositories.getInt("totalCount");
        
        //getRepo names and topics
        JSONArray repoArray = repositories.getJSONArray("edges");
        
        
        ArrayList reposArrayList = new ArrayList<Repo>();          
        for(int i = 0; i < repoArray.length(); i++){
            //get the name
            String reponame = repoArray.getJSONObject(i).getJSONObject("node").getString("name");
            
            //get topics 
            ArrayList topicsArrayList = new ArrayList<Topic>();            
            JSONArray repoTopicsArray = repoArray.getJSONObject(i).getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");
            for(int j = 0; j < repoTopicsArray.length(); j++){
                   String topicName = repoTopicsArray.getJSONObject(j).getJSONObject("node").getJSONObject("topic").getString("name");
                   topicsArrayList.add(new Topic(topicName,"urlofchoise","bla bla bla"));
                   
            }
            //add name topic and number of repos to repo list
            reposArrayList.add(new Repo(reponame,topicsArrayList,numRepos));
        }
        
        //Parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);
        

        return res.toString();
    }
}
