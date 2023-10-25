package com.ufro.Rebbird.service;

import org.springframework.stereotype.Service;

import com.ufro.Rebbird.model.ProfileImg;
import com.ufro.Rebbird.repository.ProfileImgRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileImgService {

    private final ProfileImgRepository profileImgRepository;

    public ProfileImg findById(int id) {
        return profileImgRepository.findById(id).orElse(null);
    }
}
