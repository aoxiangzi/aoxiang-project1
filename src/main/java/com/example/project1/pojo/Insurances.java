package com.example.project1.pojo;

public class Insurances {
    private String id;

    private String batch;

    private String name;

    private String phone;

    private String status;

    private String hit;

    private String time;

    private String operate;

    public Insurances() {

    }

    public Insurances(String id, String batch, String name, String phone) {
        this.batch = batch;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Insurances(String id, String batch, String name, String phone, String status, String hit, String time, String operate) {
        this.batch = batch;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.hit = hit;
        this.time = time;
        this.operate = operate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Override
    public String toString() {
        return "Insurances{" +
                "batch='" + batch + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", hit='" + hit + '\'' +
                ", time='" + time + '\'' +
                ", operate='" + operate + '\'' +
                '}';
    }
}