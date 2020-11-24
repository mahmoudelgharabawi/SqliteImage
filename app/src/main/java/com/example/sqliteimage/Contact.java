package com.example.sqliteimage;

public class Contact {

    private int id;
    private String name;
    private int phone;
    private byte[] image;


    public Contact(int id, String name, int phone, byte[] image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
    }


    public Contact(String name, int phone, byte[] image) {
        this.name = name;
        this.phone = phone;
        this.image = image;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
