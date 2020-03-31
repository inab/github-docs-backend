package com.mycompany.documentation.api.logic;

import static com.mycompany.documentation.api.logic.Constants.*;
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

/**
 *
 * @author lsimon
 */
public class JsonObj {

    public JsonObj() {
    }
    
    /**
     * It retrieves the json object from the query
     * @param jsonObj
     * @return 
     */
    public String getJsonObj(JSONObject jsonObj) {
        String res = "";
        try {
            //create the request
            HttpClient client = HttpClients.createDefault();
            //define the http verb (GET, POST, PUT..)
            HttpPost post = new HttpPost(githubApiV4);
            //add the token to the header
            post.addHeader("Authorization", "Bearer " + TOKEN);
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
