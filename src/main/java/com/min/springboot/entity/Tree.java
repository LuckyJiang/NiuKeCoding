package com.min.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.Collator;
import java.util.*;

public class Tree  {
    
    private static final Collator COLLATOR = Collator.getInstance(Locale.CHINA);
    
    private String name;
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Tree> children = new ArrayList<>();
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<String> itemIds = new HashSet<>();
    
    private boolean isHandled;
    
    private boolean isCategory;
    
    @JsonIgnore
    private String category;
    
    public Tree() {
        super();
    }
    
    public Tree(final String name, final String itemId, final boolean isCategory, final String category) {
        super();
        this.name = name;
        if (Objects.nonNull(itemId)) {
            this.itemIds.add(itemId);
        }
        
        this.isCategory = isCategory;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Tree> getChildren() {
        return children;
    }
    
    public void setChildren(final List<Tree> children) {
        this.children.addAll(children);
    }
    
    public void clearChildren() {
        this.children.clear();
    }

    public Set<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(final String itemId) {
        this.itemIds.add(itemId);
    }
    
    public void setItemIds(final Set<String> itemIds) {
        this.itemIds.addAll(itemIds);
    }
    
    @JsonIgnore
    public boolean isHandled() {
        return isHandled;
    }

    public void setIsHandled(final boolean isHandled) {
        this.isHandled = isHandled;
    }

    @JsonIgnore
    public boolean isCategory() {
        return isCategory;
    }

    public void setIsCategory(final boolean isCategory) {
        this.isCategory = isCategory;
    }
    
    public void setCategory(final String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

}
