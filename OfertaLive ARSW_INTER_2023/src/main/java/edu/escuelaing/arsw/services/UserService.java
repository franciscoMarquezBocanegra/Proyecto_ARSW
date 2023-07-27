package edu.escuelaing.arsw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.model.Usuario;
import edu.escuelaing.arsw.persistence.UserPersistence;

@Service
public class UserService {

    @Autowired
    private UserPersistence userPersistence;

    /**
     * It returns a user object from the database based on the username
     * 
     * @param username The username of the user to be found.
     * @return The userPersistence.findByUsername(username) is returning a Usuario object.
     */
    public Usuario findByUserName(String username) {
        return userPersistence.findByUsername(username);
    }

    /**
     * It returns a list of all the users in the database
     * 
     * @return A list of all the users in the database.
     */
    public List<Usuario> findAll() {
        return userPersistence.findAll();
    }

    /**
     * It creates a new user in the database
     * 
     * @param usuario The user object that will be saved in the database.
     * @return The userPersistence.save(usuario) is being returned.
     */
    public Usuario newUser(Usuario usuario) {
        return userPersistence.save(usuario);
    }
}
