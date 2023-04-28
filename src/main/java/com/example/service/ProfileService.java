package com.example.service;

import com.example.entity.EntityProfiles;

import java.util.List;

public interface ProfileService {

    void setNext(ProfileService nextHandler);
    EntityProfiles getProfile(Integer id);

    EntityProfiles saveProfile(EntityProfiles profile);

    void deleteProfile(Integer id);

    List<EntityProfiles> getAllProfile();
}

