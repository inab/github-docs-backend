package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class NumRepos {

    public NumRepos() {
    }

    JsonObj jsonObjClass = new JsonObj();

    /**
     * It retrieves the number of repos of an OWNER
     *
     * @return int num of repos
     */
    public int getNumRepos() {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + LOGIN + "\") {\n"
                + "    repositories(first: 100) {\n"
                + "      totalCount\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //get number of repos
        int numRepos = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories").getInt("totalCount");

        if (numRepos > 100) {
            numRepos = 100;
        }

        return numRepos;
    }
}
