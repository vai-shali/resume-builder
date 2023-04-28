package com.example.factory;

import com.example.entity.EntityProfiles;

import java.util.HashMap;
import java.util.Map;

public class ProfileFlyweightFactory {
    private Map<Integer, EntityProfiles> profilesCache = new HashMap<>();

    public EntityProfiles getProfile(int profileId) {
        if (profilesCache.containsKey(profileId)) {
            return profilesCache.get(profileId);
        } else {
            EntityProfiles profile = new EntityProfiles();
            profile.setId(profileId);
            profilesCache.put(profileId, profile);
            return profile;
        }
    }

//    private final Map<Integer, EntityProfiles> profiles = new HashMap<>();

    public void addProfile(EntityProfiles profile) {
        profilesCache.put(profile.getId(), profile);
    }

    public void removeProfile(Integer id) {
        profilesCache.remove(id);
    }

}
