package com.tutego.date4u.core.photo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class Photo {
    public Long id;
    @Min(1) public Long profile;
    @NotNull @Pattern(regexp = "[\\w_-]{1,200}") public String name;
    public boolean isProfilePhoto;
    @NotNull @Past public LocalDateTime created;
    public Photo(){}
    public Photo(Long id, Long profile, String name, boolean isProfilePhoto, LocalDateTime created){
        this.profile = profile;
        this.name = name;
        this.isProfilePhoto = isProfilePhoto;
        this.created = created;
    }
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
