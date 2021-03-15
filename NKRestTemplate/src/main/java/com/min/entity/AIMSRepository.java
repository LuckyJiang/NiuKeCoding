package com.min.entity;

import java.util.List;

/**
 * @author jxm
 */
public class AIMSRepository {

    private String id;

    private String name;

    private List<AIMSLabel> labels;

    private List<AIMSTask> tasks;

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

    public List<AIMSLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<AIMSLabel> labels) {
        this.labels = labels;
    }

    public List<AIMSTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<AIMSTask> tasks) {
        this.tasks = tasks;
    }
}
