package com.onion;

import com.onion.entity.User;
import com.onion.UserModel;
import com.onion.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface UserService {

    public List<User> getAllUser();

}
