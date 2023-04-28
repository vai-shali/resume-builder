package com.example.service;

import com.example.entity.EntityProfiles;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public abstract class ProfileCacheHandler implements ProfileService {

//    private Object profileRepository;
//    ProfileServiceImpl profileService = new ProfileServiceImpl(profileRepository);
//    ProfileCacheHandler cacheHandler = new ProfileCacheHandler(profileService);
//    profileService.setNext(cacheHandler);

    @Autowired
    private final ProfileService nextHandler;
    private final Map<Integer, EntityProfiles> cache = new ConcurrentHashMap<>();

    public ProfileCacheHandler(ProfileService nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void setNext(ProfileService nextHandler) {
        throw new UnsupportedOperationException("setNext() not supported on " + getClass().getSimpleName());
    }

    @Override
    public EntityProfiles getProfile(Integer id) {
        EntityProfiles profile = cache.get(id);
        if (profile == null && nextHandler != null) {
            profile = nextHandler.getProfile(id);
            if (profile != null) {
                cache.put(id, profile);
            }
        }
        return profile;
    }

    @Override
    public EntityProfiles saveProfile(EntityProfiles profile) {
        EntityProfiles savedProfile = nextHandler.saveProfile(profile);
        cache.put(savedProfile.getId(), savedProfile);
        return savedProfile;
    }

    @Override
    public void deleteProfile(Integer id) {
        cache.remove(id);
        nextHandler.deleteProfile(id);
    }

}
