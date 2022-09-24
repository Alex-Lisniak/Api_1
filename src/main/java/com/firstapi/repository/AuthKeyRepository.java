package com.firstapi.repository;

import com.firstapi.entity.AuthKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface AuthKeyRepository extends CrudRepository<AuthKey, UUID> {
}
