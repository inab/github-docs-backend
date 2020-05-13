package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repository;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class RepoQuery {

    public RepoQuery() {
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
                + "      pageInfo {\n"
                + "        startCursor\n"
                + "        endCursor\n"
                + "        hasNextPage\n"
                + "        hasPreviousPage\n"
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

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //vars repos and topics
        Repository repo = new Repository();
        String repoDescription, repoUrl, topicName, readme;
        ArrayList<String> topicsArrayList = new ArrayList();

        //vars pagination
        String startCursor, endCursor;
        Boolean hasNextPage, hasPreviousPage;

        //remove keys we don't need
        JSONObject repository = json.getJSONObject("data").getJSONObject("repository");

        //get repo name, description, url and readme
        repoName = repository.getString("name");

        if (repository.isNull("description")) {
            repoDescription = "";
        } else {
            repoDescription = repository.getString("description");
        }

        repoUrl = repository.getString("url");
        readme = repository.getJSONObject("object").getString("text");

        JSONArray topicsArray = repository.getJSONObject("repositoryTopics").getJSONArray("edges");

        for (Object oo : topicsArray) {
            JSONObject topicObj = new JSONObject(oo.toString());

            //get topic name
            topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
            topicsArrayList.add(topicName);
        }

        //get pageInfo
        JSONObject pageInfo = repository.getJSONObject("repositoryTopics").getJSONObject("pageInfo");

        //get startCursor, endCursor, hasNextPage, hasPreviousPage
        startCursor = pageInfo.getString("startCursor");
        endCursor = pageInfo.getString("endCursor");
        hasNextPage = pageInfo.getBoolean("hasNextPage");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");

        repo.setName(repoName);
        repo.setTopics(topicsArrayList);
        repo.setDescription(repoDescription);
        repo.setUrl(repoUrl);
        repo.setReadme(readme);
        repo.setStartCursor(startCursor);
        repo.setEndCursor(endCursor);
        repo.setHasPreviousPage(hasPreviousPage);
        repo.setHasNextPage(hasNextPage);

        //parse java object back to json
        JSONObject res = new JSONObject(repo);

        return res.toString();
    }
}
