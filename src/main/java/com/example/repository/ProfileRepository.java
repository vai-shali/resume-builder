package com.example.repository;

import com.example.entity.EntityProfiles;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<EntityProfiles, Integer> {

}
