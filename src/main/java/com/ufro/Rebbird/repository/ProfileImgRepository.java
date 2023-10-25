package com.ufro.Rebbird.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.ufro.Rebbird.model.ProfileImg;

@Repository
public interface ProfileImgRepository extends CrudRepository<ProfileImg, Integer> {
    
}
