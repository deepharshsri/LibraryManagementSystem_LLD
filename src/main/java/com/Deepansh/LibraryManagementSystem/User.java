package com.Deepansh.LibraryManagementSystem;

public class User {


    private String name;
    private String id;
    private String mobileNumber;
    private boolean isMember;

    public User(String name,String mobileNumber) {
        this.name = name;
        this.id = name+"_"+mobileNumber;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public User getUser(String id){
        return this;
    }
    
    public String getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }
}
