package com.muyoma.cld_gallery.media.transactions;


import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.media.data.Data;
import com.muyoma.cld_gallery.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UploadsServices {
    private UploadsServices(UploadsRepository uploadsRepository) {
        this.repository = uploadsRepository;
    }
    private UploadsRepository repository;
    Logger logger = LoggerFactory.getLogger(UploadsServices.class);
    public boolean upload(
            Collection collection,
            Data data,
            User user
    ){
        Uploads uploads = new Uploads();
        uploads.setUploadsId(
                new UploadsId(collection.getId(), user.getId(), data.getId())
        );
        uploads.setCollection(collection);
        uploads.setData(data);
        uploads.setOwner(user);
        uploads.setUploadDate(new Date());
        try{
            repository.save(uploads);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }
}
