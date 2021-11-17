package com.dma.registrationloginwithsqldb;

public class Model {
    private int id;
    private String category;
    private String sub_category;
    private String u_name;
    private String phone;
    private String address;
    private String date;


    public Model(Integer id, String category, String sub_category, String u_name, String phone, String address, String date) {
        this.category = category;
        this.sub_category = sub_category;
        this.u_name = u_name;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
