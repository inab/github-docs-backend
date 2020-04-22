package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class Pagination {

    public Pagination() {
    }

    JsonObj jsonObjClass = new JsonObj();
    NumRepos numReposClass = new NumRepos();

    public String getStartCursor() {
        int numRepos = numReposClass.getNumRepos();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: " + numRepos + ") {\n"
                + "      pageInfo {\n"
                + "        startCursor\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);
        
        //get startCursor
        String startCursor = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories").getJSONObject("pageInfo").getString("startCursor");

        return startCursor;
    }
    
    public String getEndCursor() {
        int numRepos = numReposClass.getNumRepos();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: " + numRepos + ") {\n"
                + "      pageInfo {\n"
                + "        endCursor\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);
        
        //get endCursor
        String endCursor = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories").getJSONObject("pageInfo").getString("endCursor");
        
        return endCursor;
    }
    
    public boolean getHasNextPage() {
        int numRepos = numReposClass.getNumRepos();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: " + numRepos + ") {\n"
                + "      pageInfo {\n"
                + "        hasNextPage\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //get hasNextPage
        boolean hasNextPage = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories").getJSONObject("pageInfo").getBoolean("hasNextPage");
        
        return hasNextPage;
    }
    
     public boolean getHasPreviousPage() {
        int numRepos = numReposClass.getNumRepos();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repositoryOwner(login: \"" + login + "\") {\n"
                + "    repositories(first: " + numRepos + ") {\n"
                + "      pageInfo {\n"
                + "        hasPreviousPage\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //get hasPreviousPage
        boolean hasPreviousPage = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories").getJSONObject("pageInfo").getBoolean("hasPreviousPage");

        return hasPreviousPage;
    }
}
