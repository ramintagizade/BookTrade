package com.ramin.Entity;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Book {
    @Id
    private String id;
    private String name;
    private String url;
    private String owner;
    private boolean requested;

    public Book(String name, String owner, String url,String id ) {
        this.name = name;
        this.url = url;
        this.owner = owner;
        this.id = id;
        this.requested = false;
    }
    public Book() {

    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book [" + this.id+ ", " + this.name + ", " + this.owner +", " + this.url+"]";
    }
}
