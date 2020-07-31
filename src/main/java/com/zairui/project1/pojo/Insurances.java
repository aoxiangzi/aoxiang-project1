package com.zairui.project1.pojo;

public class Insurances {
    private String id;//序号

    private String batch;//批次号

    private String name;//姓名

    private String phone;//手机号

    private String status;//状态

    private String hit;//命中情况

    private String time;//查询时间

    private String operate;//操作

    public Insurances() {
    }

    public Insurances(String id, String batch, String name, String phone) {
        this.id = id;
        this.batch = batch;
        this.name = name;
        this.phone = phone;
    }

    public Insurances(String id, String batch, String name, String phone, String status, String hit, String time, String operate) {
        this.id = id;
        this.batch = batch;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.hit = hit;
        this.time = time;
        this.operate = operate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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
                "id='" + id + '\'' +
                ", batch='" + batch + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", hit='" + hit + '\'' +
                ", time='" + time + '\'' +
                ", operate='" + operate + '\'' +
                '}';
    }
}