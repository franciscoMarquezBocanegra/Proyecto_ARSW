package edu.escuelaing.arsw.services;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.escuelaing.arsw.model.Vendedor;
import edu.escuelaing.arsw.persistence.VendedorPersistence;

@Service
public class VendedorService {
    
    @Autowired
    VendedorPersistence vendedorPersistence;

    /**
     * The function newVendedor() is a public function that returns a Vendedor object. It takes a
     * Vendedor object as a parameter. It is annotated with @Transactional. It calls the save()
     * function of the vendedorPersistence object, which is an instance of the VendedorPersistence
     * class
     * 
     * @param vendedor The object that will be persisted.
     * @return The newVendedor method is returning the vendedorPersistence.save(vendedor) method.
     */
    @Transactional
    public Vendedor newVendedor(Vendedor vendedor) {
        return vendedorPersistence.save(vendedor);
    }

    /**
     * It deletes a vendedor by id
     * 
     * @param id The id of the vendedor to be deleted.
     */
    @Transactional
    public void deleteVendedor(Long id) {
        vendedorPersistence.deleteById(id);
    }

    /**
     * If the vendedorPersistence.findById(id) returns a value, return that value, otherwise return
     * null
     * 
     * @param id The id of the vendedor to be retrieved
     * @return The method returns the vendedorPersistence.findById(id).orElse(null);
     */
    public Vendedor findById(Long id) {
        return vendedorPersistence.findById(id).orElse(null);
    }

    /**
     * This function is used to find a vendedor by its name
     * 
     * @param name The name of the method you want to call.
     * @return The method is returning a Vendedor object.
     */
    public Vendedor findByName(String name) {
        return vendedorPersistence.findByname(name);
    }

    /**
     * This function returns a list of all the vendedores in the database
     * 
     * @return A list of Vendedor objects.
     */
    public List<Vendedor> findAll() {
        return vendedorPersistence.findAll();
    }
}
