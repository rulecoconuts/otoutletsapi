package com.coconutsrule.otoutlets.outletsapi.dao;

import java.util.List;
import java.util.Random;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import com.coconutsrule.otoutlets.outletsapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements Dao<ApiUser, Integer> {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Override
    public ApiUser create(ApiUser entity) {
        entity.setSalt(generateSalt());
        return userRepo.save(getUserWithHashedPassword(entity));
    }

    /**
     * Generate a random salt
     * @return
     */
    String generateSalt() {
        int leftLimit = 48;
        int rightLimit = 122;
        int saltLength = 32;
        Random random = new Random();

        String salt = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(saltLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return salt;
    }

    /**
     * Returns a copy of a user with a hashed password
     * 
     * @param user User with plaintext password
     * @return
     */
    public ApiUser getUserWithHashedPassword(ApiUser user) {
        String hash = passwordEncoder.encode(user.getPassword() + user.getSalt());
        ApiUser encodedPasswordUser = new ApiUser();
        encodedPasswordUser.setUsername(user.getUsername());
        encodedPasswordUser.setPassword(hash);
        return encodedPasswordUser;
    }

    /**
     * Verify user credentials.
     * 
     * @param user
     * @return User with hashed password
     */
    public ApiUser verify(ApiUser user) {
        ApiUser dbUser = findByUsername(user.getUsername());
        if (dbUser == null)
            throw new UsernameNotFoundException("Username does not exist");
        user.setSalt(dbUser.getSalt());
        ApiUser hashedUser = getUserWithHashedPassword(user);

        if (!hashedUser.getPassword().equals(dbUser.getPassword()))
            throw new BadCredentialsException("Invalid password");

        return dbUser;
    }


    @Override
    public ApiUser update(ApiUser entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ApiUser> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public ApiUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void delete(ApiUser entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ApiUser find(ApiUser entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ApiUser findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

}
