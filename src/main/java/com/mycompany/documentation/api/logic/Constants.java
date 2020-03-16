/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.api.logic;

import com.mycompany.documentation.model.Links;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



/**
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */

//This class contains all the static variables.
public class Constants {
    
    
    
    
    public static String TOKEN = "";
    // This file is only on the server. It contains the Token
    static 
    {
        try(InputStream in = Constants.class.getClassLoader().getResourceAsStream("/META-INF/config.properties"))
        {
            if(in!=null){
                Properties appSettings = new Properties();
                appSettings.load(in);
                TOKEN = appSettings.getProperty("token");
            }
            
        }
        catch(IOException e)
        {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }
    
    public static String github = "https://github.com";
    public static String githubApi = "https://api.github.com/repos";
    public static String githubRaw = "https://raw.githubusercontent.com";
    public static String owner = "inab";
    public static String readmeEndpoint = "readme";

    public static ArrayList<Links> projects = new ArrayList<Links>();
    static {
        projects.add(new Links("oeb", "OPENEBENCH"));
        projects.add(new Links("wg", "WIDGET GALLERY"));
        projects.add(new Links("vre", "OEB VRE"));
    };
    
//    public static Map<String,String> projects = new HashMap<String,String>();
//    static {
//        projects.put("oeb", "OPENEBENCH");
//        projects.put("wg", "WIDGET GALLERY");
//        projects.put("vre", "OEB VRE");
//    };
    
    public static Map<String,String> vre = new HashMap<String,String>();
    static{
        vre.put("openVRE", "Open VRE");
        vre.put("vre-process_nextflow-executor", "OEB VRE Nextflow executor");
    };
    
    public static Map<String,String> oebRepos = new HashMap<String,String>();
    static{
        oebRepos.put("benchmarking-data-model","OEB data model");
        oebRepos.put("opeb-enrichers","OEB enricher");
        oebRepos.put("elixibilitas","OEB tool API");
        oebRepos.put("benchmarking","OEB validation tools");
        oebRepos.put("biotools-bioconda-ids","Bioconda ids");
        oebRepos.put("openEbench-frontend","OEB Frontend");
        oebRepos.put("TCGA_benchmarking_workflow","TCGA workflow");
        oebRepos.put("TCGA_benchmarking_dockers","TCGA dockers");
        oebRepos.put("mg-process_TCGA_CD","TCGA MG process");
    };
    public static Map<String,String> widgetGalleryRepos = new HashMap<String,String>();
    static{
        widgetGalleryRepos.put("uptime-chart-OEB","OEB uptime chart");
        widgetGalleryRepos.put("citations-widget-OEB","OEB citation chart");
        widgetGalleryRepos.put("OpenEBench_scientific_visualizer","OEB Scatter plot");
        widgetGalleryRepos.put("Scientific_Barplot","OEB Bar plot");
        widgetGalleryRepos.put("benchmarking_workflows_results_visualizer","OEB VRE results visualizer");
        widgetGalleryRepos.put("bench_event_table","OEB benchmark summay table");
    };
    
}
