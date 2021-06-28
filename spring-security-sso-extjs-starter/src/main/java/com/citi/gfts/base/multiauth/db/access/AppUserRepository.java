package com.citi.gfts.base.multiauth.db.access;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.citi.gfts.base.multiauth.db.model.AppUser;

@Component
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	List<AppUser> findByUsername(String username);
}
