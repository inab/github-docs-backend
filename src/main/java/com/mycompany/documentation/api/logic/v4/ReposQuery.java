package com.mycompany.documentation.api.logic.v4;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Contributor;
import com.mycompany.documentation.model.Repository;
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import javax.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import static sun.nio.cs.Surrogate.is;

/**
 *
 * @author lsimon
 */
public class ReposQuery {

    public ReposQuery() {
    }

    public String constructUrl(String repo) {
        return "https://api.github.com/repos/" + owner + "/" + repo + "/contributors";
    }

    public String getContributors(String repo) throws MalformedURLException, IOException {
        String urlString = constructUrl(repo);
        //URL url = new URL(urlString);
        URL url = new URL("https://api.github.com/repos/inab/Scientific_Barplot/contributors");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", "Bearer " + Constants.TOKEN);

        //option 1
        /*// Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) con.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
        String login = rootobj.getString("login"); //just grab the login
        String link = rootobj.getString("url"); //just grab the url
        Contributor cont = new Contributor(login, link);
        System.out.println(login);
        System.out.println(link);*/
        
        
        //option 2
        /*InputStream is = new URL("https://api.github.com/repos/inab/Scientific_Barplot/contributors").openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONObject json = new JSONObject(jsonText);
        return json;*/

        //option 3
        /*BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            System.out.println(inputLine);

            String login = inputLine.substring(inputLine.indexOf("login"));
            String link = inputLine.substring(inputLine.indexOf("url"));
            Contributor cont = new Contributor(login, link);
        }
        in.close();
        
        return cont.toString();*/
        
        //respuesta
        /*inputLine = [{"login"
        :"javi-gv94","id":15807311,"node_id":"MDQ6VXNlcjE1ODA3MzEx","avatar_url":"https://avatars0.githubusercontent.com/u/15807311?v=4","gravatar_id":"","url":"https://api.github.com/users/javi-gv94"},
        {"login"
        :"vsundesha","id":19426535,"node_id":"MDQ6VXNlcjE5NDI2NTM1","avatar_url":"https://avatars0.githubusercontent.com/u/19426535?v=4","gravatar_id":"","url":"https://api.github.com/users/vsundesha"}]*/

        return ""; 
    }

    NumRepos numReposClass = new NumRepos();
    NumTopics numTopicsClass = new NumTopics();
    JsonObj jsonObjClass = new JsonObj();

    public String getReposWithTopic(String[] topic) {
        int numRepos = numReposClass.getNumRepos();
        int numTopics = numTopicsClass.getNumTopics();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: " + numTopics + "){\n"
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
                + "          owner {\n"
                + "            login\n"
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
        String repoName, repoDescription, repoUrl, repoOwner, repoReadme, topicName;
        ArrayList<Repository> reposArrayList = new ArrayList<>();
        ArrayList<Repository> reposArrayList2 = new ArrayList<>();
        ArrayList<String> topicsArrayList;

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
        hasNextPage = pageInfo.getBoolean("hasNextPage");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");

        //get array of repos returned by github
        JSONArray reposArray = repositories.getJSONArray("edges");

        //iterate over array of repos, find if repositoryTopics contains topics defined by user
        for (Object o : reposArray) {
            JSONObject repoObj = new JSONObject(o.toString());

            //get array of topics from repo 
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

            //create temp list
            ArrayList<String> tmp = new ArrayList<>();
            //iterate over array of topics and add them to the tmp list
            //this is done so we can compare 2 lists: topics defined by user against topics from repo
            for (Object oo : topicsArray) {
                JSONObject topicObj = new JSONObject(oo.toString());

                //get topic name
                topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");

                //add topic name to array of topics
                tmp.add(topicName);
            }

            //compare the two lists to check if all the topics defined by user are present in topic list from repo
            if (tmp.containsAll(Arrays.asList(topic))) {
                //get repo name
                repoName = repoObj.getJSONObject("node").getString("name");

                //get repo description
                if(repoObj.getJSONObject("node").isNull("description")){
                    repoDescription = "";
                } else {
                  repoDescription = repoObj.getJSONObject("node").getString("description");
                }

                //get repo url
                repoUrl = repoObj.getJSONObject("node").getString("url");

                //get repo owner
                repoOwner = repoObj.getJSONObject("node").getJSONObject("owner").getString("login");

                //get repo readme
                repoReadme = repoObj.getJSONObject("node").getJSONObject("object").getString("text");

                //add topics to array of topics
                topicsArrayList = new ArrayList<>();
                for (String top : tmp) {
                    topicsArrayList.add(top);
                }


                //add all repo attributes to array of repos
                reposArrayList.add(new Repository(repoName, topicsArrayList, repoDescription, repoUrl, repoOwner, repoReadme, startCursor, endCursor, hasNextPage, hasPreviousPage));

                //add name and array of topics to array of repos
                reposArrayList.add(new Repository(repoName, topicsArrayList, repoDescription,  repoUrl, repoOwner, repoReadme, startCursor, endCursor, hasNextPage, hasPreviousPage));

            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }

    /*public String getReposWithoutTopic(String[] topic) {
        int numRepos = numReposClass.getNumRepos();
        int numTopics = numTopicsClass.getNumTopics();

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
                + "          name\n"
                + "          repositoryTopics(first: " + numTopics + "){\n"
                + "            edges{\n"
                + "              node{\n"
                + "                topic{\n"
                + "		     name\n"
                + "                }\n"
                + "              }\n"
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
        String repoName, topicName;
        ArrayList<Repo> reposArrayList = new ArrayList<>();
        ArrayList<String> topicsArrayList;

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
        hasNextPage = pageInfo.getBoolean("hasNextPage");
        hasPreviousPage = pageInfo.getBoolean("hasPreviousPage");

        //get array of repos returned by github
        JSONArray reposArray = repositories.getJSONArray("edges");

        //iterate over array of repos, find if repositoryTopics contains topics defined by user
        for (Object o : reposArray) {
            JSONObject repoObj = new JSONObject(o.toString());

            //get array of topics from repo 
            JSONArray topicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

            //create temp list
            ArrayList<String> tmp = new ArrayList<>();
            //iterate over array of topics and add them to the tmp list
            //this is done so we can compare 2 lists: topics defined by user against topics from repo
            for (Object oo : topicsArray) {
                JSONObject topicObj = new JSONObject(oo.toString());

                //get topic name
                topicName = topicObj.getJSONObject("node").getJSONObject("topic").getString("name");

                //add topic name to array of topics
                tmp.add(topicName);
            }

            //compare the two lists to check if all the topics defined by user are present in topic list from repo
            if (!tmp.containsAll(Arrays.asList(topic))) {
                //get repo name
                repoName = repoObj.getJSONObject("node").getString("name");

                //add topics to array of topics
                topicsArrayList = new ArrayList<>();
                for (String top : tmp) {
                    topicsArrayList.add(top);
                }

                //add name and array of topics to array of repos
                reposArrayList.add(new Repository(repoName, topicsArrayList, startCursor, endCursor, hasNextPage, hasPreviousPage));
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }*/
}
