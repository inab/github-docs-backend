package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class AllInabReposQuery {

    public AllInabReposQuery() {
    }

    NumRepos numReposClass = new NumRepos();
    JsonObj jsonObjClass = new JsonObj();

    public String getAllInabRepos() {
        int numRepos = numReposClass.getNumRepos();
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      totalCount\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          description\n"
                + "          url\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return jsonObjClass.getJsonObj(jsonObj);
    }
}
