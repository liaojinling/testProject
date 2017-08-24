package com.api.bean;

/**
 * Created by Administrator on 2017/7/27.
 */

public class Report {
    private int id;
    private String projectName;
    private String caseName;
    private String description;
    private String excepted;
    private String actual;
    private String result;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcepted() {
        return excepted;
    }

    public void setExcepted(String excepted) {
        this.excepted = excepted;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", caseName='" + caseName + '\'' +
                ", description='" + description + '\'' +
                ", excepted='" + excepted + '\'' +
                ", actual='" + actual + '\'' +
                ", result='" + result + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
