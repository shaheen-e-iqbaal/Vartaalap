package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.FollowRelationsDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRelationsRepository extends JpaRepository<FollowRelationsDTO, Long> {

    //Method to find followers of User with id = userId
    public List<FollowRelationsDTO> findByWhoIsBeingFollowed(int userId);

    //Method to find who the user with id = UserId is following
    public List<FollowRelationsDTO> findByWhoIsFollowing(int userId);

    //Method to check that whoIsFollowing already follows whoIsBeingFollowed or not
    public List<FollowRelationsDTO> findByWhoIsFollowingAndWhoIsBeingFollowed(int whoIsFollowing, int whoIsBeingFollowed);

    //Method to add Record in a table
    @Modifying
    @Transactional
    @Query(value = "insert into FollowRelationsDTO (who_is_following, who_is_being_followed) values (:whoIsFollowing, :whoIsBeingFollowed)", nativeQuery = true)
    public void follow(int whoIsFollowing, int  whoIsBeingFollowed);


    //Method to unfollow
    @Modifying
    @Transactional
    public void deleteByWhoIsFollowingAndWhoIsBeingFollowed(int whoIsFollowing, int whoIsBeingFollowed);
}
