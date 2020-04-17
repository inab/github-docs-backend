package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repo;
import com.mycompany.documentation.model.Topic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 *
 * @author lsimon
 */
public class ReposQuery {

    public ReposQuery() {
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
                + "          repositoryTopics(first: 10){\n"
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

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get pageInfo
        String startCursor, endCursor;
        Boolean hasNextPage, hasPreviousPage;
        JSONArray pagesArray = repositories.getJSONArray("pageInfo");

        //get repo names and topics
        String repoName, topicName, topicUrl, topicDescription;
        ArrayList reposArrayList = new ArrayList<Repo>();
        ArrayList topicsArrayList = new ArrayList<Topic>();
        JSONArray reposArray = repositories.getJSONArray("edges");

        for (int i = 0; i < numRepos; i++) {
            
            //get startCursor, endCursor, hasNextPage, hasPreviousPage
            startCursor = pagesArray.getJSONObject(i).getString("startCursor");
            endCursor = pagesArray.getJSONObject(i).getString("endCursor");
            hasNextPage = pagesArray.getJSONObject(i).getBoolean("hasNextPage");
            hasPreviousPage = pagesArray.getJSONObject(i).getBoolean("hasPreviousPage");

            //get name
            repoName = reposArray.getJSONObject(i).getJSONObject("node").getString("name");

            //get topics
            JSONArray topicsArray = reposArray.getJSONObject(i).getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");

            for (int j = 0; j < topicsArray.length(); j++) {
                topicName = topicsArray.getJSONObject(j).getJSONObject("node").getJSONObject("topic").getString("name");

                //add topic name to topic list
                topicsArrayList.add(new Topic(topicName));

                for (int k = 0; k < topic.length; k++) {
                    if (topicName.equalsIgnoreCase(topic[k])) {
                        reposArrayList.add(new Repo(repoName, topicsArrayList));
                    }

                        //delete reps
                        /*Repo repo;
                        do {
                            repo = new Repo(repoName, topicsArrayList);
                            //add repo name and topics to repo list
                            reposArrayList.add(repo);
                        } while (!reposArrayList.contains(repo));
                        
                        Set reposHashSet = new LinkedHashSet<Repo>();
                        reposHashSet.addAll(reposArrayList);
                        reposArrayList.clear();
                        reposArrayList.addAll(reposHashSet);
                        
                        Collections.sort(reposArrayList);
                        Iterator<Repo> it = reposArrayList.iterator();
                        Repo repo = it.next();
                        while (it.hasNext()) {
                            if (reposArrayList.contains(repo.getName())) {
                                reposArrayList.remove(repo);
                            }
                        }*/
                }
            }
        }

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }
    
    public String getReposWithTopicWithoutDuplicates(String[] topic) {
        int numRepos = numReposClass.getNumRepos();
        int numTopics = numTopicsClass.getNumTopics();
        
        JSONObject jsonObj = new JSONObject();
        
        jsonObj.put("query", "query { \n"
                + "  repositoryOwner(login: \"" + login + "\"){\n"
                + "    repositories(first: " + numRepos + "){\n"
                + "      edges{\n"
                + "        node{\n"
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
        
        // array of repositories we will return to the user
        ArrayList reposArrayList = new ArrayList<Repo>();
        

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");
        //array of all the repos retured for github
        JSONArray reposArray = repositories.getJSONArray("edges");
        
        //iterate over arrays, find if repositoryTopics contains topics defined by user.        
        for(Object o : reposArray){
            JSONObject repoObj = new JSONObject(o.toString());
            
            //Array of topics from repo 
            JSONArray repoObjTopicsArray = repoObj.getJSONObject("node").getJSONObject("repositoryTopics").getJSONArray("edges");
            
            //create temp list
            List<String> tmp =  new ArrayList<>();
            //iterate array of topics and add them to the tmp list
            //this is done so we can compare 2 lists. topics defined by user against topics from repo
            for(Object oo : repoObjTopicsArray){
                JSONObject topicsNode = new JSONObject(oo.toString());
                String topicName = topicsNode.getJSONObject("node").getJSONObject("topic").getString("name");
                tmp.add(topicName);
            }
            //here we are comparing the two lists to check if all the topics defined by user are present in topicslis from repo
            if(tmp.containsAll(Arrays.asList(topic))){
                //get name for repo
                String repoObjName = repoObj.getJSONObject("node").getString("name");
                //put the topics in array list
                ArrayList<Topic> topicss = new ArrayList<Topic>();
                for(String t : tmp){
                    topicss.add(new Topic(t,"",""));
                }
                //add name and topics array
                reposArrayList.add(new Repo(repoObjName,topicss));

            }
        }
        
        //System.out.println(topic);

        //parse java object back to json
        JSONArray res = new JSONArray(reposArrayList);

        return res.toString();
    }
}
