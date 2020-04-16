package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.*;
import com.mycompany.documentation.model.Repo;
import com.mycompany.documentation.model.Topic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

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
                + "    }\n"
                + "  }\n"
                + "}");

        String jsonString = jsonObjClass.getJsonObj(jsonObj);

        JSONObject json = new JSONObject(jsonString);

        //remove keys we don't need
        JSONObject repositories = json.getJSONObject("data").getJSONObject("repositoryOwner").getJSONObject("repositories");

        //get repo names and topics
        String repoName, topicName, topicUrl, topicDescription;
        ArrayList reposArrayList = new ArrayList<Repo>();
        ArrayList topicsArrayList = new ArrayList<Topic>();
        JSONArray reposArray = repositories.getJSONArray("edges");

        for (int i = 0; i < numRepos; i++) {
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


                        /*Repo repo;
                        do {
                            repo = new Repo(repoName, topicsArrayList);
                            //add repo name and topics to repo list
                            reposArrayList.add(repo);
                        } while (!reposArrayList.contains(repo));*/
                        //delete reps
                        
                        /*Set reposHashSet = new LinkedHashSet<Repo>();
                        reposHashSet.addAll(reposArrayList);
                        reposArrayList.clear();
                        reposArrayList.addAll(reposHashSet);*/
                        
                        /*Collections.sort(reposArrayList);
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
}
