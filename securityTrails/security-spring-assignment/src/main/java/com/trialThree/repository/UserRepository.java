/**
 * UserRepository : It has one method, which is responsible for fetching user details from the database.
 */
package com.trialThree.repository;

import com.trialThree.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}
