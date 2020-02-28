/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation.model;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
public class Links {
    
    public String link;
    public String text;

    public Links(String link, String text) {
        this.link = link;
        this.text = text;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
