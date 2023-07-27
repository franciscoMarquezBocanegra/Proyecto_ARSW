package edu.escuelaing.arsw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.model.Producto;
import edu.escuelaing.arsw.model.Vendedor;
import edu.escuelaing.arsw.persistence.ProductoPersistence;

@Service
public class ProductoService {
    @Autowired
    private ProductoPersistence productoPersistence;

    /**
     * It returns a list of all the products in the database
     * 
     * @return A list of all the products in the database.
     */
    public List<Producto> findALLProducts() {
        return productoPersistence.findAll();
    }

    /**
     * It creates a new producto in the database
     * 
     * @param producto The producto to be created.
     * @return The productoPersistence.save(producto) method returns a Producto object.
     */
    public Producto newProducto(Producto producto) {
        return productoPersistence.save(producto);
    }

    /**
     * The function takes a product name as a parameter and returns a product object
     * 
     * @param productName The name of the product to be searched.
     * @return A Producto object.
     */
    public Producto findByName(String productName) {
        return productoPersistence.findByproductName(productName);
    }

    /**
     * This function returns a list of products that have a price equal to the price passed as a
     * parameter
     * 
     * @param precio The prize of the product
     * @return A list of products.
     */
    public List<Producto> findByprize(Double precio) {
        return productoPersistence.findByPrecio(precio);
    }

    /**
     * It returns a list of products that are sold by a specific vendor
     * 
     * @param vendedor the vendedor object
     * @return A list of Producto objects.
     */
    public List<Producto> findByVendedor(Vendedor vendedor) {
        return productoPersistence.findProductosByVendedor(vendedor);
    }

    /**
     * It returns a product by its id
     * 
     * @param id The id of the producto to be retrieved
     * @return A Producto object.
     */
    public Producto productoById(Long id) {
        return productoPersistence.findById(id).orElse(null);
    }

    /**
     * This function deletes a product by its id
     * 
     * @param id The id of the product to delete.
     */
    public void deleteProduct(Long id) {
        productoPersistence.deleteById(id);
    }
}

