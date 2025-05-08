package com.muyoma.cld_gallery.grouping.membership;



import com.muyoma.cld_gallery.grouping.group.Group;
import com.muyoma.cld_gallery.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "membership")
public class Membership {

    @Id
    private MemberShipId id;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id" , nullable = false)
    private User member;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id" , nullable = false)
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate = new Date();



    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Membership( User member, Group group) {
        this.member = member;
        this.group = group;
    }
    public Membership() {}
}
