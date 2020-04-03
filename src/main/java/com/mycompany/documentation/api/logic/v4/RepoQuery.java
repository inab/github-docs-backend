package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.owner;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class RepoQuery {

    public RepoQuery() {
    }

    public String getDataFromARepoWithoutParams() {
        String msg = "Add a path param {repoName}";

        return msg;
    }

    public String getDataFromARepo(String repoName) {
        JSONObject jsonObj = new JSONObject();
        JsonObj jsonObjClass = new JsonObj();
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

        return jsonObjClass.getJsonObj(jsonObj);
    }
}
