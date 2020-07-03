package cn.com.entity;

import java.io.Serializable;

public class Buy implements Serializable {

    private int id;
    private int did;
    private int uid;
    private int number;
    private int state ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "id=" + id +
                ", did=" + did +
                ", uid=" + uid +
                ", number=" + number +
                ", state=" + state +
                '}';
    }
}
