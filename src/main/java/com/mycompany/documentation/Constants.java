/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;



/**
 *  This class contains all the static variables
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public class Constants {
    
    
    
    public Constants() {
    }
    static String TOKEN = "";
    
    public static void load()
     {
        try
        {
            Properties appSettings = new Properties();
            FileInputStream fis = new FileInputStream("root to config file"); //put config properties file to buffer    
            appSettings.load(fis); //load config.properties file
            TOKEN = appSettings.getProperty("token");
            System.out.println(TOKEN);
            fis.close();
        }
        catch(IOException e)
        {
            System.out.println("Could not load settings file.");
            System.out.println(e.getMessage());
        }
    }
    
    static String github = "https://github.com";
    static String githubApi = "https://api.github.com/repos";
    static String githubRaw = "https://raw.githubusercontent.com";
    static String owner = "inab";
    static String readmeEndpoint = "readme";
    public static String[] oebRepos = {
        "benchmarking-data-model",
        "opeb-enrichers",
        "openEBenchAPI",
        "elixibilitas",
        "benchmarking",
        "biotools-bioconda-ids",
        "opeb-submission",
        "openEbench-frontend",
        "TCGA_benchmarking_workflow",
        "TCGA_benchmarking_dockers",
        "mg-process_TCGA_CD",
    };
    public static String[] widgetGalleryRepos = {
        "uptime-chart-OEB",
        "citations-widget-OEB",
        "OpenEBench_scientific_visualizer",
        "Scientific_Barplot",
        "benchmarking_workflows_results_visualizer",
    };
    
}
