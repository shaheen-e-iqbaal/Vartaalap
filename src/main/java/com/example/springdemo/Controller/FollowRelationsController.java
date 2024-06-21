package com.example.springdemo.Controller;

import com.example.springdemo.Service.FollowRelationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/followrelations")
public class FollowRelationsController {

    FollowRelationsService followRelationsService;

    public FollowRelationsController(FollowRelationsService followRelationsService){
        this.followRelationsService = followRelationsService;
    }

    @GetMapping(path = "/followersof")
    public List<Integer> findFollowers(@RequestParam int userId){
        return followRelationsService.findFollowers(userId);
    }

    @GetMapping(path = "/followingto")
    public List<Integer> findFollowing(@RequestParam int userId){
        return followRelationsService.findWhoIsBeingFollowed(userId);
    }

    @PostMapping(path = "/follow")
    public String follow(@RequestParam int whoIsFollowing,
                         @RequestParam int whoIsBeingFollowed){
        return followRelationsService.follow(whoIsFollowing, whoIsBeingFollowed);
    }

    @PostMapping(path = "/unfollow")
    public String unFollow(@RequestParam int whoIsFollowing,
                           @RequestParam int whoIsBeingFollowed){
        return followRelationsService.unFollow(whoIsFollowing, whoIsBeingFollowed);
    }

}
