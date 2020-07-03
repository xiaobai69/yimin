package cn.com.entity;

import java.io.Serializable;

public class Drug implements Serializable {
    private int id;
    private String drugName;
    private String type;
    private String mainFunction;
    private int count;
    private int nowCount;
    private String madeTime;
    private int totalTime;
    private float joinMoney;
    private float saleMoney;
    private String note;
    private int state;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNowCount() {
        return nowCount;
    }

    public void setNowCount(int nowCount) {
        this.nowCount = nowCount;
    }

    public String getMadeTime() {
        return madeTime;
    }

    public void setMadeTime(String madeTime) {
        this.madeTime = madeTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public float getJoinMoney() {
        return joinMoney;
    }

    public void setJoinMoney(float joinMoney) {
        this.joinMoney = joinMoney;
    }

    public float getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(float saleMoney) {
        this.saleMoney = saleMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", type='" + type + '\'' +
                ", mainFunction='" + mainFunction + '\'' +
                ", count=" + count +
                ", nowCount=" + nowCount +
                ", madeTime='" + madeTime + '\'' +
                ", totalTime=" + totalTime +
                ", joinMoney=" + joinMoney +
                ", saleMoney=" + saleMoney +
                ", note='" + note + '\'' +
                ", state=" + state +
                ", number=" + number +
                '}';
    }
}
