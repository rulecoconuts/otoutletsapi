package com.coconutsrule.otoutlets.outletsapi.dao;

import java.util.List;
import com.coconutsrule.otoutlets.outletsapi.models.User;
import com.coconutsrule.otoutlets.outletsapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements Dao<User, Integer> {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Override
    public User create(User entity) {
        return userRepo.save(getUserWithHashedPassword(entity));
    }

    /**
     * Returns a copy of a user with a hashed password
     * @param user User with plaintext password
     * @return
     */
    public User getUserWithHashedPassword(User user){
        String hash = passwordEncoder.encode(user.getPassword());
        User encodedPasswordUser = new User();
        encodedPasswordUser.setUsername(user.getUsername());
        encodedPasswordUser.setPassword(hash);
        return encodedPasswordUser;
    }

    @Override
    public User update(User entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User find() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }
    
}
