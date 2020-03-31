package com.mycompany.documentation.api.logic;

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

    public static String TOKEN = "";

    // This file is only on the server. It contains the Token
    static {
        try (InputStream in = Constants.class.getClassLoader().getResourceAsStream("/META-INF/config.properties")) {
            if (in != null) {
                Properties appSettings = new Properties();
                appSettings.load(in);
                TOKEN = appSettings.getProperty("token");
            }

        } catch (IOException e) {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }

    public static String github = "https://github.com";
    public static String githubApi = "https://api.github.com/repos";
    public static String githubApiV4 = "https://api.github.com/graphql";
    public static String githubRaw = "https://raw.githubusercontent.com";
    public static String owner = "inab";
    public static String login = "inab";
    public static String readmeEndpoint = "readme";

    //Topics
    public static Topic backend = new Topic("Backend", "backend", "Code that runs on our servers or we use internally");
    public static Topic frontend = new Topic("Frontend", "frontend", "All kinds of web applications");
    public static Topic scientific = new Topic("Scientific", "scientific", "Everything that is related to the scientific benchmarking aspect of OEB");
    public static Topic technical = new Topic("Technical", "technical", "Everything that is related to the technical monitoring aspect of OEB");
    public static Topic vre = new Topic("VRE", "vre", "Code that is used anywhere in the VRE");
    public static Topic api = new Topic("API", "api", "All the REST endpoints Technical and Scientific used by any component of OEB");
    public static Topic workflow = new Topic("Workflow", "workflow", "The set of processes that define a benchmarking event in OEB");
    public static Topic visualizer = new Topic("Visualizer", "visualizer", "Components or widgets created for OEB");
    public static Topic data_model = new Topic("Data model", "data_model", "Json schemas defined by OEB");
    public static Topic level1 = new Topic("Level1", "level1", "OEB architecture level 1. Storage and visualization of benchmarking results");
    public static Topic level2 = new Topic("Level2", "level2", "OEB architecture level 2. Computation of metrics using benchmarking workflows and the VRE");
    public static Topic level3 = new Topic("Level3", "level3", "OEB architecture level 3. Computation of the whole experiment in the platform: predictions, metrics, storage and visualization");

    public static ArrayList<Topic> topics = new ArrayList();

    static {
        topics.add(backend);
        topics.add(frontend);
        topics.add(scientific);
        topics.add(technical);
        topics.add(vre);
        topics.add(api);
        topics.add(workflow);
        topics.add(visualizer);
        topics.add(data_model);
        topics.add(level1);
        topics.add(level2);
        topics.add(level3);
        
        /*topics.add(new Topic("Backend", "backend", "Code that runs on our servers or we use internally"));
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
        topics.add(new Topic("Level3", "level3", "OEB architecture level 3. Computation of the whole experiment in the platform: predictions, metrics, storage and visualization"));*/
    }

    public static ArrayList<Link> projects = new ArrayList();

    static {
        projects.add(new Link("wg", "WIDGET GALLERY"));
        projects.add(new Link("vre", "OEB VRE"));
        projects.add(new Link("oeb", "OPENEBENCH"));
    }

    public static ArrayList<Link> wgRepos = new ArrayList();
    
    static {
        wgRepos.add(new Link("uptime-chart-OEB", "OEB uptime chart"));
        wgRepos.add(new Link("citations-widget-OEB", "OEB citation chart"));
        wgRepos.add(new Link("OpenEBench_scientific_visualizer", "OEB Scatter plot"));
        wgRepos.add(new Link("Scientific_Barplot", "OEB Bar plot"));
        wgRepos.add(new Link("benchmarking_workflows_results_visualizer", "OEB VRE results visualizer"));
        wgRepos.add(new Link("bench_event_table", "OEB benchmark summay table"));
    }

    public static ArrayList<Link> vreRepos = new ArrayList();

    static {
        vreRepos.add(new Link("openVRE", "Open VRE"));
        vreRepos.add(new Link("vre-process_nextflow-executor", "OEB VRE Nextflow executor"));
    }

    public static ArrayList<Link> oebRepos = new ArrayList();

    static {
        oebRepos.add(new Link("benchmarking-data-model", "OEB data model"));
        oebRepos.add(new Link("opeb-enrichers", "OEB enricher"));
        oebRepos.add(new Link("elixibilitas", "OEB tool API"));
        oebRepos.add(new Link("benchmarking", "OEB validation tools"));
        oebRepos.add(new Link("biotools-bioconda-ids", "Bioconda ids"));
        oebRepos.add(new Link("openEbench-frontend", "OEB Frontend"));
        oebRepos.add(new Link("TCGA_benchmarking_workflow", "TCGA workflow"));
        oebRepos.add(new Link("TCGA_benchmarking_dockers", "TCGA dockers"));
        oebRepos.add(new Link("mg-process_TCGA_CD", "TCGA MG process"));
    }
}
