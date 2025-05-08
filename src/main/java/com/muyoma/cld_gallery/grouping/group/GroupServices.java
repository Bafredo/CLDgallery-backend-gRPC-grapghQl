package com.muyoma.cld_gallery.grouping.group;



import com.muyoma.cld_gallery.grouping.membership.MemberShipRepository;
import com.muyoma.cld_gallery.grouping.membership.Membership;
import com.muyoma.cld_gallery.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GroupServices {

    Logger logger = LoggerFactory.getLogger(GroupServices.class);

    private GroupRepository repository;
    private MemberShipRepository membershipRepository;
    public GroupServices(GroupRepository repositor,MemberShipRepository membershipRepository) {
        this.repository = repository;
        this.membershipRepository = membershipRepository;
    }

    public GroupServices() {}

    public boolean addGroup(String name, String description) {
        try{
            repository.save(new Group(name, description));
            logger.info(name + " added");
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }

    }

    public boolean deleteGroup(Long id) {
        try{
            repository.deleteById(id);
            logger.info(id + " deleted");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean addGroupMember(Group group, User u) {
        try{
            membershipRepository.save(
                    new Membership( u,group)
            );
            logger.info("Added group membership");
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
    public boolean removeGroupMember(Group group, User u) {
        try{
            Membership m = membershipRepository.findByMemberAndGroup(u, group);
            membershipRepository.delete(m);
            logger.info("Removed group membership");
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

}
