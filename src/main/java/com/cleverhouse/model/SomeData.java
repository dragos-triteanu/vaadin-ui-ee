package com.cleverhouse.model;

/**
 * Created by xPku on 9/24/16.
 */
public class SomeData {


    private String id;

    private String name;

    public SomeData(String id, String name) {
        this.id = id;
        this.name = name;
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
}
