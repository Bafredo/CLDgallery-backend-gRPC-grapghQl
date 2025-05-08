package com.muyoma.cld_gallery.media.collections;


import com.muyoma.cld_gallery.media.transactions.Uploads;
import com.muyoma.cld_gallery.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Uploads> uploads;

    public List<Uploads> getUploads() {
        return uploads;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Collection(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
    public Collection() {}

    public CollectionDto toDto(){
        if (uploads.isEmpty()){
            return new CollectionDto(
                    getId(),
                    getName(),getDescription(),
                    null
            );
        } else {
            return new CollectionDto(
                    getId(),
                    getName(),getDescription(),
                    uploads.get(0).getData().getUrl()
            );
        }

    }
}
