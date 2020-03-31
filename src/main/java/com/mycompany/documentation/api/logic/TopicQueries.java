package com.mycompany.documentation.api.logic;

import com.mycompany.documentation.model.*;
import java.util.Iterator;
import org.json.JSONObject;

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

        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");
        JSONObject numRepos = repositories.getJSONObject("totalCount");
        JSONObject repoName = repositories.getJSONObject("edges").getJSONObject("node").getJSONObject("name");
        JSONObject topics = repositories.getJSONObject("edges").getJSONObject("node").getJSONObject("repositoryTopics").getJSONObject("edges").getJSONObject("node").getJSONObject("topic").getJSONObject("name");

        Repo repo = new Repo();
        repo.setName(repoName.toString());
        repo.setTotalCount(Integer.parseInt(numRepos.toString()));
                
        /*Iterator<String> keys = repositories.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (repositories.get(key) instanceof JSONObject) {
                System.out.println(key);      
            }
        }*/
        return repositories.toString();
    }
}
