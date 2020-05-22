package com.mycompany.documentation.api.logic.v4;

import static com.mycompany.documentation.api.logic.v4.Constants.owner;
import com.mycompany.documentation.model.Contributor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lsimon
 */
public class ContributorsQuery {

    public String constructUrl(String repo) {
        return "https://api.github.com/repos/" + owner + "/" + repo + "/contributors";
    }

    public JSONObject getContributors(String repo) throws MalformedURLException, IOException {
        String urlString = constructUrl(repo);
        URL url = new URL(urlString); //https://api.github.com/repos/inab/benchmarking-data-model/contributors
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", "Bearer " + Constants.TOKEN);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ArrayList<Contributor> conts = new ArrayList<>();
        
        JSONArray contArray = new JSONArray(response.toString());
        for (Object o : contArray) {
            System.out.println(o);
            JSONObject contObj = new JSONObject(o.toString());

            String user = contObj.getString("login");
            String link = contObj.getString("url");

            conts.add(new Contributor(user, link));
        }

        JSONObject resp = new JSONObject(conts);
        System.out.println(resp);

        return resp;
    }
}
