package com.muyoma.cld_gallery.grouping.collectionaccess;


import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.user.User;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "collection_access")
public class CollectionAccess {
    @EmbeddedId
    CollectionAccessId id;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "collection_id")
    Collection collection;

    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate = new Date();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public CollectionAccess(Collection collection, User user) {
        this.collection = collection;
        this.user = user;
        this.id = new CollectionAccessId(user.getId(), collection.getId());
    }

    public CollectionAccess() {}
}
