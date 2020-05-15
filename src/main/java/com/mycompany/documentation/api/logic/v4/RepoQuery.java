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

    JsonObj jsonObjClass = new JsonObj();

    public String getRepo(String repoName) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query {\n"
                + "  repository(name: \"" + repoName + "\", owner: \"" + owner + "\") {\n"
                + "    id\n"
                + "    name\n"
                + "    repositoryTopics(first: 10) {\n"
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
                + "    languages(first: 10) {\n"
                + "      edges {\n"
                + "        node {\n"
                + "          name\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "    licenseInfo {\n"
                + "      key\n"
                + "    }\n"
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
        String repoId, repoDescription, repoUrl, repoLicense, repoReadme, topicName, languageName;
        ArrayList<String> topicsArrayList = new ArrayList();
        ArrayList<String> languagesArrayList = new ArrayList();

        //vars pagination
        String startCursor, endCursor;
        Boolean hasNextPage, hasPreviousPage;

        //remove keys we don't need
        JSONObject repository = json.getJSONObject("data").getJSONObject("repository");

        //get repo id, description, url, license and readme
        repoId = repository.getString("id");
        if (repository.isNull("description")) {
            repoDescription = "";
        } else {
            repoDescription = repository.getString("description");
        }
        repoUrl = repository.getString("url");
        if (repository.isNull("licenseInfo")) {
            repoLicense = "";
        } else {
            repoLicense = repository.getJSONObject("licenseInfo").getString("key");
        }
        if (repository.isNull("object")) {
            repoReadme = "";
        } else {
            repoReadme = repository.getJSONObject("object").getString("text");
        }

        //get array of topics from repo
        JSONArray topicsArray = repository.getJSONObject("repositoryTopics").getJSONArray("edges");

        for (Object o : topicsArray) {
            JSONObject topicObj = new JSONObject(o.toString());

            //get topic name
            topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
            //add topic name to array of topics
            topicsArrayList.add(topicName);
        }

        //get array of languages from repo
        JSONArray languagesArray = repository.getJSONObject("languages").getJSONArray("edges");

        for (Object oo : languagesArray) {
            JSONObject languageObj = new JSONObject(oo.toString());

            //get language name
            languageName = languageObj.getJSONObject("node").getString("name");
            //add language name to array of languages
            languagesArrayList.add(languageName);
        }

        //get pageInfo
        JSONObject pageInfo = repository.getJSONObject("repositoryTopics").getJSONObject("pageInfo");

        //get startCursor, endCursor, hasNextPage, hasPreviousPage
        startCursor = pageInfo.getString("startCursor");
        endCursor = pageInfo.getString("endCursor");
        hasNextPage = pageInfo.getBoolean("hasNextPage");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");

        repo.setId(repoId);
        repo.setName(repoName);
        repo.setTopics(topicsArrayList);
        repo.setDescription(repoDescription);
        repo.setUrl(repoUrl);
        repo.setLanguages(languagesArrayList);
        repo.setLicense(repoLicense);
        repo.setReadme(repoReadme);
        repo.setStartCursor(startCursor);
        repo.setEndCursor(endCursor);
        repo.setHasPreviousPage(hasPreviousPage);
        repo.setHasNextPage(hasNextPage);

        //parse java object back to json
        JSONObject res = new JSONObject(repo);

        return res.toString();
    }
}
