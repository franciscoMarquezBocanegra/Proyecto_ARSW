package edu.escuelaing.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.escuelaing.arsw.model.Producto;
import edu.escuelaing.arsw.model.Vendedor;

@Repository
public interface ProductoPersistence extends JpaRepository<Producto, Long> {
    /**
     * It returns a Producto object that has a productName that matches the productName parameter
     * 
     * @param productName The name of the product to search for.
     * @return A Producto object
     */
    public Producto findByproductName(String productName);

    /**
     * Find all products with a price greater than or equal to the given price.
     * 
     * @param precio The price of the product.
     * @return A list of Producto objects.
     */
    public List<Producto> findByPrecio(Double precio);

    /**
     * Find all products where the product's seller is equal to the seller passed in as a parameter.
     * 
     * @param vendedor the Vendedor object that is the seller of the products
     * @return A list of Producto objects.
     */
    @Query("select products from Producto products where products.vendedor = ?1")
    public List<Producto> findProductosByVendedor(Vendedor vendedor);

}
