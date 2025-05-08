package com.muyoma.cld_gallery.media.transactions;


import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadsRepository extends JpaRepository<Uploads, Long> {

    public List<Uploads> findByCollection(Collection collection);

    public List<Uploads> findByOwnerAndCollection(User user, Collection collection);

}
