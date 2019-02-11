package io.github.trtong.deepindebt.repository;

import io.github.trtong.deepindebt.domain.UserDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the UserDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query("select user_details from UserDetails user_details where user_details.user.login = ?#{principal.username}")
    List<UserDetails> findByUserIsCurrentUser();

}
