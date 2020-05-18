package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.login;
import com.mycompany.documentation.model.Repository;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class AllReposQuery {

    public AllReposQuery() {
    }

    NumRepos numReposClass = new NumRepos();
    JsonObj jsonObjClass = new JsonObj();

    public JSONArray getAllReposWithTopic(ArrayList<String> topics) {
        int numRepos = numReposClass.getNumRepos();
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          id\n"
                + "          name\n"
                + "          repositoryTopics(first: 10){\n"
                + "            edges{\n"
                + "              node{\n"
                + "                topic{\n"
                + "		     name\n"
                + "                }\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "          description\n"
                + "          url\n"
                + "          languages(first: 10) {\n"
                + "            edges {\n"
                + "              node {\n"
                + "                name\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "          licenseInfo {\n"
                + "            key\n"
                + "          }\n"
                + "          object(expression: \"master:README.md\") {\n"
                + "            ... on Blob {\n"
                + "              text\n"
                + "            }\n"
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
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);
        JSONObject json = new JSONObject(jsonString);

        //vars repos and topics
        String repoId, repoName, repoDescription, repoUrl, repoLicense, repoReadme, topicName, languageName;
        ArrayList<Repository> reposArrayList = new ArrayList<>();
        ArrayList<String> topicsArrayList;
        ArrayList<String> languagesArrayList;

        //vars pagination
        String startCursor, endCursor;
        Boolean hasNextPage, hasPreviousPage;

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get pageInfo
        JSONObject pageInfo = repositories.getJSONObject("pageInfo");

        //get startCursor, endCursor, hasNextPage, hasPreviousPage
        startCursor = pageInfo.getString("startCursor");
        endCursor = pageInfo.getString("endCursor");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");
        hasNextPage = pageInfo.getBoolean("hasNextPage");

        //get array of repos returned by github
        JSONArray reposArray = repositories.getJSONArray("edges");

        for (Object o : reposArray) {
            JSONObject repoObj = new JSONObject(o.toString());

            //get repo id, name, description, url, license and readme
            repoId = repoObj.getJSONObject("node").getString("id");
            repoName = repoObj.getJSONObject("node").getString("name");
            if (repoObj.getJSONObject("node").isNull("description")) {
                repoDescription = "";
            } else {
                repoDescription = repoObj.getJSONObject("node").getString("description");
            }
            repoUrl = repoObj.getJSONObject("node").getString("url");
            if (repoObj.getJSONObject("node").isNull("licenseInfo")) {
                repoLicense = "";
            } else {
                repoLicense = repoObj.getJSONObject("node").getJSONObject("licenseInfo").getString("key");
            }
            if (repoObj.getJSONObject("node").isNull("object")) {
                repoReadme = "";
            } else {
                repoReadme = repoObj.getJSONObject("node").getJSONObject("object").getString("text");
            }

            //get array of languages from repo
            JSONArray languagesArray = repoObj.getJSONObject("node").getJSONObject("languages").getJSONArray("edges");
            languagesArrayList = new ArrayList<>();
            for (Object ooo : languagesArray) {
                JSONObject languageObj = new JSONObject(ooo.toString());

                //get language name
                languageName = languageObj.getJSONObject("node").getString("name");
                //add language name to array of languages
                languagesArrayList.add(languageName);
            }

            //get array of topics from repo
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

            if (topics.isEmpty()) {
                topicsArrayList = new ArrayList<>();
                for (Object oo : topicsArray) {
                    JSONObject topicObj = new JSONObject(oo.toString());

                    //get topic name
                    topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
                    //add topic name to array of topics
                    topicsArrayList.add(topicName);
                }

                //add all repo attributes to array of repos
                reposArrayList.add(new Repository(repoId, repoName, topicsArrayList, repoDescription, repoUrl, languagesArrayList, repoLicense, repoReadme, startCursor, endCursor, hasPreviousPage, hasNextPage));
            } else {
                ArrayList<String> tmp = new ArrayList<>();
                for (Object oo : topicsArray) {
                    JSONObject topicObj = new JSONObject(oo.toString());

                    //get topic name
                    topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");
                    //add topic name to array of topics
                    tmp.add(topicName);
                }

                if (tmp.containsAll(topics)) {
                    //add topics to array of topics
                    topicsArrayList = new ArrayList<>();
                    for (String top : tmp) {
                        topicsArrayList.add(top);
                    }

                    //add all repo attributes to array of repos
                    reposArrayList.add(new Repository(repoId, repoName, topicsArrayList, repoDescription, repoUrl, languagesArrayList, repoLicense, repoReadme, startCursor, endCursor, hasPreviousPage, hasNextPage));
                }
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        //return res.toString();
        return res;
    }
}
