package com.example.service;

import com.example.entity.EntityProfiles;
import com.example.repository.ProfileRepository;
import com.example.factory.ProfileFlyweightFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    protected ProfileRepository profileRepository;

    private ProfileService nextHandler;

    @Override
    public void setNext(ProfileService nextHandler) {
        this.nextHandler = nextHandler;
    }

    private final ProfileFlyweightFactory flyweightFactory = new ProfileFlyweightFactory();

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public EntityProfiles getProfile(Integer id) {
        EntityProfiles flyweightProfile = flyweightFactory.getProfile(id);
        if (flyweightProfile != null) {
            return flyweightProfile;
        }
        Optional<EntityProfiles> byId = profileRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else if (nextHandler != null) {
            return nextHandler.getProfile(id);
        }
        EntityProfiles profile = byId.get();
        flyweightFactory.addProfile(profile);
        return profile;
    }

    @Override
    public EntityProfiles saveProfile(EntityProfiles profile) {
        EntityProfiles savedProfile = profileRepository.save(profile);
        flyweightFactory.addProfile(savedProfile);
        return savedProfile;
    }

    @Override
    public void deleteProfile(Integer id) {
        profileRepository.deleteById(id);
        flyweightFactory.removeProfile(id);
    }

    @Override
    public List<EntityProfiles> getAllProfile() {
        return (List<EntityProfiles>) profileRepository.findAll();
    }

//    @PostConstruct
//    public void configureProfileHandlerChain() {
//        ProfileCacheHandler cacheHandler = new ProfileCacheHandler(this);
//        this.setNext(cacheHandler);
//    }

}