package com.muyoma.cld_gallery.media.transactions;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class UploadsId implements Serializable {

    private Long collectionId;
    private Long ownerId;
    private Long dataId;
    public UploadsId() {}
    public UploadsId(Long collectionId, Long ownerId, Long dataId) {
        this.collectionId = collectionId;
        this.ownerId = ownerId;
        this.dataId = dataId;


    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadsId that = (UploadsId) o;
        return Objects.nonNull(collectionId) && Objects.nonNull(ownerId) && Objects.nonNull(dataId);
    }
    @Override
    public int hashCode(){
        return Objects.hash(collectionId, ownerId, dataId);
    }
}
