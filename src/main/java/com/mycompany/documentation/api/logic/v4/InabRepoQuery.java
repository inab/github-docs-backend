package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class InabRepoQuery {

    public InabRepoQuery() {
    }

    NumTopics numTopicsClass = new NumTopics();
    JsonObj jsonObjClass = new JsonObj();

    public String getInabRepo(String repoName) {
        int numTopics = numTopicsClass.getNumTopics();
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repository(name: \"" + repoName + "\", owner: \"" + owner + "\") {\n"
                + "    name\n"
                + "    repositoryTopics(first: " + numTopics + ") {\n"
                + "      edges {\n"
                + "        node {\n"
                + "          topic {\n"
                + "            name\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "    description\n"
                + "    url\n"
                + "    object(expression: \"master:README.md\") {\n"
                + "      ... on Blob {\n"
                + "        text\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return jsonObjClass.getJsonObj(jsonObj);
    }
}
