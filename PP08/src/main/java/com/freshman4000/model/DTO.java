package com.freshman4000.model;

public class DTO extends User {
    private String prevEmail;
    private String prevPass;

    public String getPrevEmail() {
        return prevEmail;
    }

    public void setPrevEmail(String prevEmail) {
        this.prevEmail = prevEmail;
    }

    public String getPrevPass() {
        return prevPass;
    }

    public void setPrevPass(String prevPass) {
        this.prevPass = prevPass;
    }
}
