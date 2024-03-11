package com.tutego.date4u.core.photo;

import java.time.LocalDateTime;

public class Photo {
    public Long id;
    public Long profile;
    public String name;
    public boolean isProfilePhoto;
    public LocalDateTime created;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProfile() {
        return profile;
    }
    public void setProfile(Long profile) {
        this.profile = profile;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isProfilePhoto() {
        return isProfilePhoto;
    }
    public void setProfilePhoto(boolean isProfilePhoto) {
        this.isProfilePhoto = isProfilePhoto;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
}
