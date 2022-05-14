package xyz.parkh.with.mogakco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUserListAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

}
