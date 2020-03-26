package com.mycompany.documentation.api.logic;

import static com.mycompany.documentation.api.logic.Constants.githubApiV4;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadmeQueryV4 {

    public ReadmeQueryV4() {
    }

    public String getReadmeAndUrlByReponameAndOwner(String repoName, String owner) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("query", "query { \n"
                + "  repository(name: \"" + repoName + "\", owner: \"" + owner + "\") {\n"
                + "    url\n"
                + "    object(expression: \"master:README.md\") {\n"
                + "      ... on Blob {\n"
                + "        text\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}");

        return getJsonObj(jsonObj);
    }

    public String getJsonObj(JSONObject jsonObj) {
        String res = "";
        try {
            //create the request
            HttpClient client = HttpClients.createDefault();
            //define the http verb (GET, POST, PUT..)
            HttpPost post = new HttpPost(githubApiV4);
            //add the token to the header
            post.addHeader("Authorization", "Bearer " + Constants.TOKEN);
            post.addHeader("Accept", "application/json");
            //add json object to post request
            post.setEntity(new StringEntity(jsonObj.toString()));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as string
                String result = EntityUtils.toString(entity);
                res = result;
            }
            return res;
        } catch (IOException | JSONException e) {
            System.out.println(e);
        }

        return res;
    }
}
