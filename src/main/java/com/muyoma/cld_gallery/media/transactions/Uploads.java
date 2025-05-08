package com.muyoma.cld_gallery.media.transactions;



import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.media.data.Data;
import com.muyoma.cld_gallery.user.User;
import jakarta.persistence.*;

import javax.print.attribute.standard.Media;
import java.util.Date;

@Entity
@Table(name = "Uploads")
public class Uploads {
    @EmbeddedId
    UploadsId uploadsId;


    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "collection_id" , nullable = false)
    Collection collection;

    @ManyToOne
    @MapsId("dataId")
    @JoinColumn(name = "data_id" , nullable = false)
    Data data;

    @ManyToOne
    @MapsId("ownerId")
    @JoinColumn(name = "owner_id" , nullable = false)
    User owner;

    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate = new Date();


    public UploadsId getUploadsId() {
        return uploadsId;
    }

    public void setUploadsId(UploadsId uploadsId) {
        this.uploadsId = uploadsId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
