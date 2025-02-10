package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class CenterDTO {
    private long id;
    private String title;
    private String address;
    private String contacts;

    public CenterDTO(Long id, String title, String contacts, String address) {
        this.id = id;
        this.title = title;
        this.contacts = contacts;
        this.address = address;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

}
