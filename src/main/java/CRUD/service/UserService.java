package CRUD.service;

/**
 * Created by sanzhar on 8/23/16.
 */

import CRUD.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUser(int id);
    List<User> getUsers(Long page);
    List<User> getUsers(String name);
    List<User> getAdmins();
}
