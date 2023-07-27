package edu.escuelaing.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.escuelaing.arsw.model.Comprador;

public interface CompradorPersistence extends JpaRepository<Comprador, Long> {
    /**
     * Find a Comprador by name.
     * 
     * @param name The name of the method.
     * @return A Comprador object
     */
    public Comprador findByname(String name);
}
