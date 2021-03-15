package com.min.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author jxm
 */
public class AIMSHandle {

    private String id;

    private String source;

    private String itemId;

    private List<String> handles = Lists.newArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<String> getHandles() {
        return handles;
    }

    public void setHandles(List<String> handles) {
        this.handles = handles;
    }
}
