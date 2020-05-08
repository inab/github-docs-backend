package com.mycompany.documentation.model;

/**
 *
 * @author lsimon
 */
public class Contributor {
    private String login;
    private String url;

    public Contributor() {
    }

    public Contributor(String login, String url) {
        this.login = login;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
