package com.webapp.cybersecurityforum.persistence;

import com.webapp.cybersecurityforum.domain.entity.UserInfoEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfoEntity, Long> {

}