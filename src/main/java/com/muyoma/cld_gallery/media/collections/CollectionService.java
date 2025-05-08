package com.muyoma.cld_gallery.media.collections;

import com.muyoma.cld_gallery.user.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectionService {
    private CollectionRepository repo;

    public CollectionService(CollectionRepository repo) {
        this.repo = repo;
    }

    public boolean createCollection(String name ,String description, User owner) {
        try{
            repo.save(new Collection(name, description, owner));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteCollection(Long id) {
        try{
            Collection collection = repo.findById(id).get();
            repo.delete(collection);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Collection getCollectionById(Long id) {
        return repo.findById(id).get();
    }
    public Collection getCollectionByName(String name,User owner) {
        return repo.findCollectionByNameAndOwner(name,owner);
    }
    public List<Collection> getAll() {
        return repo.findAll();
    }
}
