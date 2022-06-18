package xyz.parkh.with.mogakco.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUserListAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUser(String userNum) {
        return userRepository.findByUserNum(userNum);
    }

}
