package com.muyoma.cld_gallery.grouping.collectionaccess;



import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.media.collections.CollectionDto;
import com.muyoma.cld_gallery.media.collections.CollectionRepository;
import com.muyoma.cld_gallery.media.data.DataDto;
import com.muyoma.cld_gallery.media.transactions.Uploads;
import com.muyoma.cld_gallery.media.transactions.UploadsRepository;
import com.muyoma.cld_gallery.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CollectionAccessServices {
    private CollectionAccessRepository repo;
    private CollectionRepository collectionRepo;

    private UploadsRepository uploadsRepo;
    Logger logger = LoggerFactory.getLogger(CollectionAccessServices.class);

    public CollectionAccessServices(CollectionAccessRepository repo, CollectionRepository collectionRepo, UploadsRepository uploadsRepo) {
        this.repo = repo;
        this.collectionRepo = collectionRepo;
        this.uploadsRepo = uploadsRepo;
    }

    public CollectionAccess findByUserAndCollection(User user, Collection collection) {
        try{
            return repo.findByUserAndCollection(user, collection);
        }catch(Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public boolean addMemberToCollection(Collection collection, User user) {
        try{
            repo.save(
                    new CollectionAccess(collection, user)
            );
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
    public boolean removeMemberFromCollection(Collection collection, User user) {
        try {
            CollectionAccess ca = repo.findByUserAndCollection(user, collection);
            repo.delete(ca);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<CollectionDto> findUserCollections(User user) {
        try{
            List<CollectionAccess> c = repo.findByUser(user);
            List<CollectionDto> collections = new ArrayList<>();
            c.forEach(c1 -> {
                collectionRepo.findById(c1.id.getCollectionId()).ifPresent(collection -> {
                    collections.add(collection.toDto());
                });
            });
            user.getCollections().forEach(collection -> {
                collections.add(collection.toDto());
            });
            return collections;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    public List<DataDto> findUserData(User user) {
        try{
            List<CollectionAccess> c = repo.findByUser(user);
            List<Collection> collections = new ArrayList<>();
            c.forEach(c1 -> {
                collectionRepo.findById(c1.id.getCollectionId()).ifPresent(collections::add);
            });
            collections.addAll(user.getCollections());
            List<Uploads> ups= new ArrayList<>();
            collections.forEach(collection -> {
                ups.addAll(uploadsRepo.findByCollection(collection));
            });
            List<DataDto> dataDtos = new ArrayList<>();
            ups.forEach(upload -> {
                dataDtos.add(upload.getData().toDto());
            });
            return dataDtos;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    public Collection findByUser(User user, String name) {
        try {
            for (CollectionAccess c : repo.findByUser(user)) {
                if (Objects.equals(c.collection.getName(), name)) {
                    return c.collection;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
