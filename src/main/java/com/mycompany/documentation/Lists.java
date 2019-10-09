/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
enum Repos
{
    OEB,
    WIDGET
}

public class Lists {
    Repos repos;
    
    
    String[] oebRepos = {
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
    
    static String[] widgetGalleryRepos = {
        "benchmarking-data-model",
        "opeb-enrichers",
        "openEBenchAPI",
    };
    
    public Lists(Repos repos){
        this.repos = repos;
    }
    
    public String[] getList(){
        String [] list = oebRepos;
        switch(repos){
            case OEB: 
                list =  oebRepos;
                break;
            case WIDGET:
                list =  widgetGalleryRepos;
                break;
            default:
                break;
        }
        return list;
    }
    
    
}
