package com.ufro.Rebbird.service;

import com.ufro.Rebbird.model.ProfileImg;
import com.ufro.Rebbird.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufro.Rebbird.commons.GenericService;
import com.ufro.Rebbird.model.User;

@Service
public class UserService extends GenericService<User, Integer> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileImgService profileImgService;

    public UserService(UserRepository repository) {
        super(repository);
    }

    public User findByUserName(String name) {
        return ((UserRepository) repository).findByUsername(name);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void changeDescripcion(int id, String descripcion) {
        userRepository.findById(id).map(x -> {
            x.setDescripcion(descripcion);
            return userRepository.save(x);
        });
    }

    public void changeImgPerfil(int id, int img) {
        ProfileImg profileImg = profileImgService.findById(img);
        if (profileImg != null) {
            userRepository.findById(id).map(x -> {
                x.setProfileImg(profileImg);
                return userRepository.save(x);
            });
        }
    }

    public void changeUsername(int id, String username) {
        userRepository.findById(id).map(x -> {
            x.setUsername(username);
            return userRepository.save(x);
        });
    }
}