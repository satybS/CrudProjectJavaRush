package CRUD.data;

import CRUD.User;

import java.util.Date;
import java.util.List;

/**
 * Created by sanzhar on 8/23/16.
 */
public interface UserRepository {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUser(int id);
    List<User> getUsers(Long page);
    List<User> getUsers(String name);
    List<User> getUsers(int age);
    List<User> getUsers(Date dateCreated);
    List<User> getAdmins();
}