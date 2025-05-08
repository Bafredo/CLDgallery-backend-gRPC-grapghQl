package com.muyoma.cld_gallery.grouping.collectionaccess;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class CollectionAccessId implements Serializable {

    Long userId;
    Long collectionId;

    public CollectionAccessId(Long userId, Long collectionId) {
        this.userId = userId;
        this.collectionId = collectionId;
    }
    public CollectionAccessId(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionAccessId that = (CollectionAccessId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(collectionId, that.collectionId);
    }


    @Override
    public int hashCode(){
        return Objects.hash(collectionId, userId);
    }

}
