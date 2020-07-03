package cn.com.entity;

import java.io.Serializable;

public class News implements Serializable {

    private int id;
    private String title;
    private String contents;
    private String inputTime;
    private String inputName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents=" + contents +
                ", inputTime='" + inputTime + '\'' +
                ", inputName='" + inputName + '\'' +
                '}';
    }
}
