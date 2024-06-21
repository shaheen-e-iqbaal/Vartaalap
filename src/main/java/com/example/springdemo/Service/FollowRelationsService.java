package com.example.springdemo.Service;


import com.example.springdemo.DTO.FollowRelationsDTO;
import com.example.springdemo.Repository.FollowRelationsRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowRelationsService {

    FollowRelationsRepository followRelationsRepository;

    public FollowRelationsService(FollowRelationsRepository followRelationsRepository){
        this.followRelationsRepository = followRelationsRepository;
    }

    public List<Integer> findFollowers(int userId){
        List<FollowRelationsDTO> resp = followRelationsRepository.findByWhoIsBeingFollowed(userId);
        List<Integer> ans = new ArrayList<>();
        for(var i : resp){
            ans.add(i.getWhoIsFollowing());
        }
        return ans;
    }

    public List<Integer> findWhoIsBeingFollowed(int userId){
        List<FollowRelationsDTO> resp = followRelationsRepository.findByWhoIsFollowing(userId);
        List<Integer> ans = new ArrayList<>();
        for(var i : resp){
            ans.add(i.getWhoIsBeingFollowed());
        }
        return ans;
    }

    public Boolean isFollowing(int whoIsFollowing, int whoIsBeingFollowed){
        int sz = followRelationsRepository.findByWhoIsFollowingAndWhoIsBeingFollowed(whoIsFollowing,whoIsBeingFollowed).size();
        if(sz == 1)return true;
        return false;
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
