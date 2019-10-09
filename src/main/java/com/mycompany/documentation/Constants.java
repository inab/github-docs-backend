/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

/**
 *  This class contains all the static variables
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public class Constants {

    public Constants() {
    }
    
    
    
    static String github = "https://github.com";
    static String githubApi = "https://api.github.com/repos";
    static String githubRaw = "https://raw.githubusercontent.com";
    static String owner = "inab";
    static String readmeEndpoint = "readme";
    static enum organization {
        OEB,
        WIDGET,
    }
    public static String[] oebRepos = {
        "benchmarking-data-model",
        "opeb-enrichers",
        "openEBenchAPI",
        "elixibilitas",
        "benchmarking",
        "biotools-bioconda-ids",
        "opeb-submission",
        "openEbench-frontend",
        "OpenEBench_scientific_visualizer",
        "TCGA_benchmarking_workflow",
        "TCGA_benchmarking_dockers",
        "benchmarking_workflows_results_visualizer",
        "mg-process_TCGA_CD",
    };
    public static String[] widgetGalleryRepos = {
        "benchmarking-data-model",
        "opeb-enrichers",
        "openEBenchAPI",
    };
}
