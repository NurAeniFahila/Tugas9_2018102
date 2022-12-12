package com.example.a2018102_tugas5_recyclerview;

public class Model {
    private int id;
    private byte[]proavatar;
    private String userdevisi;
    private String userstar;
    private String userjoin;
    //constructor
    public Model(int id, byte[] proavatar, String userdevisi,
                     String userstar, String userjoin) {
        this.id = id;
        this.proavatar = proavatar;
        this.userdevisi = userdevisi;
        this.userstar = userstar;
        this.userjoin = userjoin;
    }
    //getter and setter method
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public byte[] getProavatar() {
        return proavatar;
    }
    public void setProavatar(byte[] proavatar) {
        this.proavatar = proavatar;
    }
    public String getUserdevisi() {
        return userdevisi;
    }
    public void setUserdevisi(String userdevisi) {
        this.userdevisi = userdevisi;
    }
    public String getUserstar() {
        return userstar;
    }
    public void setUserstar(String userstar) {
        this.userstar = userstar;
    }
    public String getUserjoin() {
        return userjoin;
    }
    public void setUserprice(String userjoin) {
        this.userjoin = userjoin;
    }
}
