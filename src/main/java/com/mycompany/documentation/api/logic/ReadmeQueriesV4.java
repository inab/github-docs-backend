package com.mycompany.documentation.api.logic;

import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class ReadmeQueriesV4 {

    public ReadmeQueriesV4() {
    }
    
    public String getJsonObj(JSONObject jsonObj) {
        JsonObj jsonObjClass = new JsonObj();
        return jsonObjClass.getJsonObj(jsonObj);
    }

    public String getReadmeFromARepo(String repoName, String owner) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repository(name: \"" + repoName + "\", owner: \"" + owner + "\") {\n"
                + "    name\n"
                + "    url\n"
                + "    object(expression: \"master:README.md\") {\n"
                + "      ... on Blob {\n"
                + "        text\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return getJsonObj(jsonObj);
    }

    public String getReadmesFromAllRepos(String login) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login:\"" + login + "\"){\n"
                + "    repositories(first:5){\n"
                + "      totalCount\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          url\n"
                + "          object(expression: \"master:README.md\") {\n"
                + "            ... on Blob {\n"
                + "              text\n"
                + "            }\n"
                + "    	     }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return getJsonObj(jsonObj);
    }
}
