package com.arens.userportal.helloworld;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean() {

    }

    public HelloWorldBean(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
