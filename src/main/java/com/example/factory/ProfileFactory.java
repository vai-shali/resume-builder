package com.example.factory;

import com.example.entity.EntityProfiles;

public class ProfileFactory implements ProfileFactoryInterface{

    EntityProfiles profile = new EntityProfiles();
    @Override
    public EntityProfiles createProfile(EntityProfiles profile) {
        this.profile = profile;
        return profile;
    }
}
