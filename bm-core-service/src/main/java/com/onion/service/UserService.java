package com.onion.service;

import com.onion.entity.User;
import com.onion.model.UserModel;
import com.onion.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserModel> getAllUser(){
        try{
            List<User> users = userRepository.findAll();
            List<UserModel> usermodels = new ArrayList<>();
            users.stream().forEach(e ->{
                UserModel user = new UserModel();
                BeanUtils.copyProperties(e,user);
                usermodels.add(user);
            });
            return usermodels;
        }catch (Exception e){
            throw e;
        }
    }
}
