/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.documentation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Vicky Sundesha <vicky.sundesha@bsc.es>
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //Notification that the servlet context is about to be shut down.   
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //Notification that the web application initialization process is starting
        //Constants.load();
    }
}
