package com.chu.xieproject.xieproject.entity;

public class Users {
        private Integer uid;
        private String name;
        private String pwd;
        private String permission;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Users(){

    }

    public Users(Integer uid, String name, String pwd, String permission) {
        this.uid = uid;
        this.name = name;
        this.pwd = pwd;
        this.permission = permission;
    }
}
