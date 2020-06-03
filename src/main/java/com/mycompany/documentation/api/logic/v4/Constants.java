package com.mycompany.documentation.api.logic.v4;

import com.mycompany.documentation.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
//This class contains all the static variables.
public class Constants {

    public static String TOKEN, GITHUBAPIV4, LOGIN, OWNER, PROJECT;

    // This file is only on the server. It contains the OEB configuration
    static {
        try (InputStream in = Constants.class.getClassLoader().getResourceAsStream("/META-INF/oebConfig.properties")) {
            if (in != null) {
                Properties appSettings = new Properties();
                appSettings.load(in);
                TOKEN = appSettings.getProperty("token");
                GITHUBAPIV4 = appSettings.getProperty("githubApiV4");
                LOGIN = appSettings.getProperty("login");
                OWNER = appSettings.getProperty("owner");
                PROJECT = appSettings.getProperty("project");
            }

        } catch (IOException e) {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Topic> topics = new ArrayList();

    static {
        topics.add(new Topic("Backend", "backend", "Code that runs on our servers or we use internally"));
        topics.add(new Topic("Frontend", "frontend", "All kinds of web applications"));
        topics.add(new Topic("Scientific", "scientific", "Everything that is related to the scientific benchmarking aspect of OEB"));
        topics.add(new Topic("Technical", "technical", "Everything that is related to the technical monitoring aspect of OEB"));
        topics.add(new Topic("VRE", "vre", "Code that is used anywhere in the VRE"));
        topics.add(new Topic("API", "api", "All the REST endpoints Technical and Scientific used by any component of OEB"));
        topics.add(new Topic("Workflow", "workflow", "The set of processes that define a benchmarking event in OEB"));
        topics.add(new Topic("Visualizer", "visualizer", "Components or widgets created for OEB"));
        topics.add(new Topic("Data model", "data_model", "Json schemas defined by OEB"));
        topics.add(new Topic("Level1", "level1", "OEB architecture level 1. Storage and visualization of benchmarking results"));
        topics.add(new Topic("Level2", "level2", "OEB architecture level 2. Computation of metrics using benchmarking workflows and the VRE"));
        topics.add(new Topic("Level3", "level3", "OEB architecture level 3. Computation of the whole experiment in the platform: predictions, metrics, storage and visualization"));
    }
}
