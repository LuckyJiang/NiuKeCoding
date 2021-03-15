package com.min.entity;

import java.util.List;

/**
 * @author jxm
 */
public class AIMSItem {

    private String repositoryId;

    private String id;

    private String parentId;

    private String  categoryName;

    private List<AIMSItem> children;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<AIMSItem> getChildren() {
        return children;
    }

    public void setChildren(List<AIMSItem> children) {
        this.children = children;
    }
}
