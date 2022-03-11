package ca.sait.jdbc.services;

import ca.sait.jdbc.dataaccess.UserDB;
import ca.sait.jdbc.models.Role;
import ca.sait.jdbc.models.User;
import java.util.List;

public class UserService {
    private UserDB UserDB = new UserDB();
    
    public User get(String email) throws Exception {
        User user = this.UserDB.get(email);
        return user;
    }
    
    public List<User> getAll() throws Exception {
        List<User> users = this.UserDB.getAll();
        return users;
    }
    
    public boolean insert(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, active, firstName, lastName, password, role);
        return this.UserDB.insert(user);
    }
    
    public boolean update(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, active, firstName, lastName, password, role);
        return this.UserDB.update(user);
    }
    
    public boolean delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        return this.UserDB.delete(user);
    }
}