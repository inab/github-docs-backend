package com.mycompany.documentation.api.logic;

import static com.mycompany.documentation.api.logic.Constants.*;
import org.json.JSONObject;

/**
 *
 * @author tarda
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
        
        for (int i = 0; i < oebRepos.size(); i++) {
            if (topic.equals(oebRepos.get(i).getTags())){
                
            }
        }

        return getJsonObj(jsonObj);
    }
}
