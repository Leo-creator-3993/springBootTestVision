package com.future.rocket.springboottest.vision.repository;

import com.future.rocket.springboottest.vision.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
