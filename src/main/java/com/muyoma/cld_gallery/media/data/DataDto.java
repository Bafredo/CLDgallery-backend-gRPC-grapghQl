package com.muyoma.cld_gallery.media.data;

import java.util.List;

public class DataDto {
    public DataDto(String title, String description, String url,List<String> collections) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.collections = collections;
    }

    private String title;
    private String description;
    private String url;

    private List<String> collections;

    public List<String> getCollectionName() {
        return collections;
    }
    public void setCollectionName(List<String> collections) {
        this.collections = collections;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
