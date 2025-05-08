package com.muyoma.cld_gallery.media.data;



import com.muyoma.cld_gallery.media.transactions.Uploads;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String url;

    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Uploads> collection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Uploads> getCollection() {
        return collection;
    }

    public void setCollection(List<Uploads> collection) {
        this.collection = collection;
    }

    public Data(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }
    public Data() {}
    public DataDto toDto(){
        List<String> collection = new ArrayList<>();
        getCollection().forEach(upload -> {
           collection.add( upload.getCollection().getName());
        });
        return new DataDto(title, description, url,collection);
    }
}
