package com.app.notifications.repository;

import com.app.notifications.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByFirstName(String firstName);
}
