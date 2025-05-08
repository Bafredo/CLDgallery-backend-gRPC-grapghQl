package com.muyoma.cld_gallery.grouping.membership;


import com.muyoma.cld_gallery.grouping.group.Group;
import com.muyoma.cld_gallery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberShipRepository extends JpaRepository<Membership, Long> {

    Membership findByMemberAndGroup(User member, Group group);
}
