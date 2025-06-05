package com.example.Vartaalap.Controller;

import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Service.FollowRelationsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/followrelations")
public class FollowRelationsController {

    FollowRelationsService followRelationsService;

    public FollowRelationsController(FollowRelationsService followRelationsService) {
        this.followRelationsService = followRelationsService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/followersof")
    public List<User> findFollowers(@RequestParam int userId) {
        return followRelationsService.findFollowers(userId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/followingto")
    public List<User> findFollowing(@RequestParam int userId) {
        return followRelationsService.findFollowing(userId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/follow")
    public String follow(@RequestParam int whoIsFollowing, @RequestParam int whoIsBeingFollowed) {
        return followRelationsService.follow(whoIsFollowing, whoIsBeingFollowed);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/unfollow")
    public String unFollow(@RequestParam int whoIsFollowing, @RequestParam int whoIsBeingFollowed) {
        return followRelationsService.unfollow(whoIsFollowing, whoIsBeingFollowed);
    }

}
