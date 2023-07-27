package edu.escuelaing.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.escuelaing.arsw.model.Usuario;

public interface UserPersistence extends JpaRepository<Usuario, Long> {
    /**
     * Find a user by their username.
     * 
     * @param username The username of the user you want to find.
     * @return A user with the username passed as parameter.
     */
    public Usuario findByUsername(String username);
}
