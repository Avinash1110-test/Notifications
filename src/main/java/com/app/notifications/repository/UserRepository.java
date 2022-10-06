package com.app.notifications.repository;

import com.app.notifications.model.QUserDetails;
import com.app.notifications.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>, QuerydslPredicateExecutor<UserDetails>, QuerydslBinderCustomizer<QUserDetails> {

    UserDetails findByFirstName(String firstName);
}
