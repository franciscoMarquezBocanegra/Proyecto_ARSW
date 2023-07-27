package edu.escuelaing.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.escuelaing.arsw.model.Vendedor;

public interface VendedorPersistence extends JpaRepository<Vendedor, Long>{
    /**
     * Find a Vendedor by name.
     * 
     * @param name The name of the method you want to create.
     * @return a Vendedor object.
     */
    public Vendedor findByname(String name);
}
