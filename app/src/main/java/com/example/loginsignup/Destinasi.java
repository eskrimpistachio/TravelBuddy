package com.example.loginsignup;

public class Destinasi {

    private String key, name, location, photos, desc ;

    public Destinasi(){};

    public Destinasi(String name, String location, String photos, String desc){
        this.name = name ;
        this.location = location ;
        this.photos = photos ;
        this.desc = desc ;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
