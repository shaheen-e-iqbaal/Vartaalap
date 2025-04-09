package com.example.Vartaalap.Service;


import com.example.Vartaalap.DTO.FollowRelationsDTO;
import com.example.Vartaalap.DTO.UserDTO;
import com.example.Vartaalap.Repository.FollowRelationsRepository;
import com.example.Vartaalap.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowRelationsService {

    FollowRelationsRepository followRelationsRepository;

    public FollowRelationsService(FollowRelationsRepository followRelationsRepository){
        this.followRelationsRepository = followRelationsRepository;
    }

    public List<UserDTO> findFollowers(int userId){
        List<FollowRelationsDTO> resp = followRelationsRepository.findByWhoIsBeingFollowed(userId);
        List<UserDTO> ans = new ArrayList<>();
        for(var i : resp){
            ans.add(i.getWhoIsFollowing());
        }
        return ans;
    }

    public List<UserDTO> findWhoIsBeingFollowed(int userId){
        List<FollowRelationsDTO> resp = followRelationsRepository.findByWhoIsFollowing(userId);
        List<UserDTO> ans = new ArrayList<>();
        for(var i : resp){
            ans.add(i.getWhoIsBeingFollowed());
        }
        return ans;
    }

    public Boolean isFollowing(int whoIsFollowing, int whoIsBeingFollowed){
        int sz = followRelationsRepository.findByWhoIsFollowingAndWhoIsBeingFollowed(whoIsFollowing,whoIsBeingFollowed).size();
        return sz == 1;
    }

    public String follow (int whoIsFollowing, int whoIsBeingFollowed){
        boolean check = isFollowing(whoIsFollowing, whoIsBeingFollowed);
        if(check)return "Already Following";
        followRelationsRepository.follow(whoIsFollowing, whoIsBeingFollowed);
        return "Successfully Followed";

    }

    public String unFollow(int whoIsFollowing, int whoIsBeingFollowed){
        if(isFollowing(whoIsFollowing, whoIsBeingFollowed)){
            followRelationsRepository.deleteByWhoIsFollowingAndWhoIsBeingFollowed(whoIsFollowing, whoIsBeingFollowed);
            return "Successfully Unfollowed";
        }
        return "Already Not following";
    }

}
