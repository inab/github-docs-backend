package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class NumTopics {

    public NumTopics() {
    }

    NumRepos numReposClass = new NumRepos();
    JsonObj jsonObjClass = new JsonObj();

    /**
     * It retrieves the number of topics of a repo
     *
     * @return int num of topics
     */
    public int getNumTopics() {
        int numRepos = numReposClass.getNumRepos();
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

        String jsonString = jsonObjClass.getJsonObj(jsonObj);

        JSONObject json = new JSONObject(jsonString);

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        int numTopics = 0;
        JSONArray reposArray = repositories.getJSONArray("edges");
        for (int i = 0; i < numRepos; i++) {
            //get number of topics 
            numTopics = reposArray.getJSONObject(i).getJSONObject("node").getJSONObject("repositoryTopics").getInt("totalCount");
        }

        if (numTopics > 10) {
            numTopics = 10;
        }

        return numTopics;
    }
}
