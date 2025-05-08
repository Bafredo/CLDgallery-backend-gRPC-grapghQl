package com.muyoma.cld_gallery.grouping.membership;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
class MemberShipId implements Serializable {


    private Long userId;
    private Long groupId;

    public MemberShipId(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }
    public MemberShipId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberShipId that = (MemberShipId) o;
        return Objects.nonNull(userId) && Objects.nonNull(groupId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId, groupId);
    }


}
