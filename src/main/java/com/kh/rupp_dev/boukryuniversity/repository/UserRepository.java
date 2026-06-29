package com.kh.rupp_dev.boukryuniversity.repository;

import java.util.Optional;

import com.kh.rupp_dev.boukryuniversity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	 Optional<User> findByFullName(String fullName);

	 Optional<User> findByEmail(String email);

	 Optional<User> findByVerificationToken(String token);
}
