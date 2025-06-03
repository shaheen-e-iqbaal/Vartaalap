package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.FollowRelations;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Repository.FollowRelationsRepository;
import com.example.Vartaalap.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowRelationsService {

    private final FollowRelationsRepository followRelationsRepository;
    private final UserRepository userRepository;

    public FollowRelationsService(FollowRelationsRepository followRelationsRepository, UserRepository userRepository) {
        this.followRelationsRepository = followRelationsRepository;
        this.userRepository = userRepository;
    }

    public List<User> findFollowers(int userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) return new ArrayList<>();

        List<FollowRelations> followers = followRelationsRepository.findByWhoIsBeingFollowed(user);
        List<User> result = new ArrayList<>();
        for (FollowRelations relation : followers) {
            result.add(relation.getWhoIsFollowing());
        }
        return result;
    }

    public List<User> findFollowing(int userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) return new ArrayList<>();

        List<FollowRelations> following = followRelationsRepository.findByWhoIsFollowing(user);
        List<User> result = new ArrayList<>();
        for (FollowRelations relation : following) {
            result.add(relation.getWhoIsBeingFollowed());
        }
        return result;
    }

    public boolean isFollowing(int whoIsFollowingId, int whoIsBeingFollowedId) {
        return !followRelationsRepository.findByWhoIsFollowingUserIdAndWhoIsBeingFollowedUserId(whoIsFollowingId, whoIsBeingFollowedId).isEmpty();
    }

    public String follow(int whoIsFollowingId, int whoIsBeingFollowedId) {
        if (isFollowing(whoIsFollowingId, whoIsBeingFollowedId)) {
            return "Already Following";
        }

        User follower = userRepository.findByUserId(whoIsFollowingId);
        User following = userRepository.findByUserId(whoIsBeingFollowedId);
        if (follower == null || following == null) return "Invalid User(s)";


        FollowRelations relation = new FollowRelations();
        relation.setWhoIsFollowing(follower);
        relation.setWhoIsBeingFollowed(following);

        followRelationsRepository.save(relation);
        return "Successfully Followed";
    }

    public String unfollow(int whoIsFollowingId, int whoIsBeingFollowedId) {
        if (!isFollowing(whoIsFollowingId, whoIsBeingFollowedId)) {
            return "Already Not Following";
        }

        User follower = userRepository.findByUserId(whoIsFollowingId);
        User following = userRepository.findByUserId(whoIsBeingFollowedId);
        if (follower == null || following == null) return "Invalid User(s)";

        followRelationsRepository.deleteByWhoIsFollowingAndWhoIsBeingFollowed(follower, following);
        return "Successfully Unfollowed";
    }
}
