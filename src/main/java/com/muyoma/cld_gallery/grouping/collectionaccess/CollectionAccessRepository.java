package com.muyoma.cld_gallery.grouping.collectionaccess;

import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionAccessRepository extends JpaRepository<CollectionAccess, Long> {
    public CollectionAccess findByUserAndCollection(User user, Collection collection);

    public List<CollectionAccess> findByUser(User user);
}
