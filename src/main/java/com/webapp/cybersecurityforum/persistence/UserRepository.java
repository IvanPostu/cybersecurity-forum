package com.webapp.cybersecurityforum.persistence;

import com.webapp.cybersecurityforum.domain.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

}