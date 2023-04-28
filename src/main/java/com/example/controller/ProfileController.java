package com.example.controller;

import com.example.entity.EntityProfiles;
import com.example.factory.ProfileFactory;
import com.example.factory.ProfileFactoryInterface;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;

    protected ProfileFactoryInterface profileFactory = new ProfileFactory();

    @GetMapping("/profile/{profileId}")
    public EntityProfiles get(@PathVariable("profileId") Integer profileId) {
        return profileService.getProfile(profileId);
    }

    @GetMapping("/profile")
    public List<EntityProfiles> getAll() {
        return profileService.getAllProfile();
    }

    @PostMapping("/profile") //creation
    public EntityProfiles create(@RequestBody EntityProfiles profile) {
        return profileService.saveProfile(profileFactory.createProfile(profile));
    }

    @DeleteMapping("/profile/{profileId}")
    public void delete(@PathVariable("profileId") Integer profileId) {
        profileService.deleteProfile(profileId);
    }

    @PatchMapping("/profile")
    public EntityProfiles update(@RequestBody EntityProfiles profile) { return profileService.saveProfile(profile); }

}



