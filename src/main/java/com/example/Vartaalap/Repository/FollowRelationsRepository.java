package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.FollowRelations;
import com.example.Vartaalap.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface FollowRelationsRepository extends JpaRepository<FollowRelations, Long> {

    List<FollowRelations> findByWhoIsBeingFollowed(User user);

    List<FollowRelations> findByWhoIsFollowing(User user);

    List<FollowRelations> findByWhoIsFollowingUserIdAndWhoIsBeingFollowedUserId(int userId1, int userId2);


    @Modifying
    @Transactional
    void deleteByWhoIsFollowingAndWhoIsBeingFollowed(User whoIsFollowing, User whoIsBeingFollowed);
}
