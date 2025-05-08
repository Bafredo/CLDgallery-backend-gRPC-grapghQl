package com.muyoma.cld_gallery.media.collections;

import com.muyoma.cld_gallery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findCollectionByNameAndOwner(String name, User owner);

}
