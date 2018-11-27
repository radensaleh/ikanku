package com.mafish.mafish.model;

public class User {

    private String id;
    private String name;
    private String no_hp;
    private String password;

    public User(String id, String name, String no_hp, String password) {
        this.id = id;
        this.name = name;
        this.no_hp = no_hp;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
