package CRUD.service;

/**
 * Created by sanzhar on 8/23/16.
 */

import CRUD.User;
import CRUD.data.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceConcrete implements UserService {
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userRepository.removeUser(id);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers(Long page) {
        return userRepository.getUsers(page);
    }

    @Override
    @Transactional
    public List<User> getUsers(String name) {
        return userRepository.getUsers(name);
    }

    @Override
    @Transactional
    public List<User> getUsers(int age) {
        return userRepository.getUsers(age);
    }

    @Override
    @Transactional
    public List<User> getUsers(Date dateCreated) {
        return userRepository.getUsers(dateCreated);
    }

    @Override
    @Transactional
    public List<User> getAdmins() {
        return userRepository.getAdmins();
    }
}

