package edu.escuelaing.arsw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.model.Comprador;
import edu.escuelaing.arsw.persistence.CompradorPersistence;

@Service
public class CompradorService {
    
    @Autowired
    private CompradorPersistence compradorPersistence;

    /**
     * It creates a new Comprador object and saves it to the database
     * 
     * @param comprador The object to be persisted.
     * @return The newComprador method is returning the comprador object.
     */
    @Transactional
    public Comprador newComprador(Comprador comprador) {
        return compradorPersistence.save(comprador);
    }

    /**
     * It deletes a comprador by its id
     * 
     * @param id The id of the entity to delete
     */
    @Transactional
    public void deleteComprador(Long id) {
        compradorPersistence.deleteById(id);
    }

    /**
     * It returns a Comprador object if it exists, otherwise it returns null
     * 
     * @param id The id of the entity
     * @return The object of the class Comprador.
     */
    public Comprador findById(Long id) {
        return compradorPersistence.findById(id).orElse(null);
    }

    /**
     * This function is used to find a comprador by name
     * 
     * @param name The name of the method you want to call.
     * @return The method returns a Comprador object.
     */
    public Comprador findByName(String name) {
        return compradorPersistence.findByname(name);
    }
}
