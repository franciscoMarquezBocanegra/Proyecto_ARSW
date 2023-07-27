package edu.escuelaing.arsw.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arsw.model.Producto;
import edu.escuelaing.arsw.model.Vendedor;
import edu.escuelaing.arsw.services.VendedorService;
import edu.escuelaing.arsw.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/Vendedor")
@CrossOrigin("*")
public class VendedorController {
    
    @Autowired
    VendedorService vendedorService;

    @Autowired
    ProductoService productoService;

    /**
     * It returns a list of all the vendedores in the database
     * 
     * @return A list of Vendedor objects.
     */
    @GetMapping("/vendedor")
    public ResponseEntity<?> getALLVendedores() {
        return new ResponseEntity<>(vendedorService.findAll(), HttpStatus.ACCEPTED);
    }

    /**
     * It takes a string as a parameter, and returns a response entity with the vendedor object and a
     * status code
     * 
     * @param name The name of the vendedor you want to retrieve.
     * @return A ResponseEntity object.
     */
    @GetMapping("/vendedor/{name}")
    public ResponseEntity<?> getVendedorByName(@PathVariable String name) {
        Vendedor vendedor = vendedorService.findByName(name);
        return new ResponseEntity<>(vendedor, HttpStatus.ACCEPTED);
    }

    /**
     * It returns a product by its id
     * 
     * @param id Long
     * @return A ResponseEntity object.
     */
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductsVendedorById(@PathVariable Long id) {
        try {
            Producto producto = productoService.productoById(id);
            return new ResponseEntity<>(producto, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * It creates a new product and assigns it to a seller
     * 
     * @param producto is the object that I want to save in the database
     * @param id the id of the seller
     * @return A ResponseEntity object.
     */
    @PostMapping("/productos/{id}")
    public ResponseEntity<?> createProduct(@RequestBody Producto producto, @PathVariable Long id) {
        Producto newProduct = null;
        try {
            Vendedor vendedor = vendedorService.findById(id);
            if (vendedor == null) {
                return new ResponseEntity<>("No se encontro el vendedor", HttpStatus.BAD_REQUEST);
            }
            producto.setVendedor(vendedor);
            newProduct = productoService.newProducto(producto);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);

        } catch (Exception ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * The function receives a product object and an id, it then searches for the product with the
     * given id, if it doesn't find it, it returns a 404 error, if it does find it, it updates the
     * product with the new information and returns the updated product
     * 
     * @param producto is the object that I'm sending from the frontend
     * @param id The id of the product to be updated
     * @return The productoActualizado object is being returned.
     */
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Producto producto, @PathVariable Long id) {
        Producto productoActualizado = null;
        try {
            Producto productoActual = productoService.productoById(id);
            if (productoActual == null) {
                return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
            }
            productoActual.setProductName(producto.getProductName());
            productoActual.setDescripcion(producto.getDescripcion());
            productoActual.setPrecio(producto.getPrecio());
            productoActual.setFechaCreacion(producto.getFechaCreacion());
            productoActualizado = productoService.newProducto(productoActual);

            return new ResponseEntity<>(productoActualizado, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * The function deletes a product from the database
     * 
     * @param id The id of the product to be deleted
     * @return A ResponseEntity object.
     */
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productoService.deleteProduct(id);
            return new ResponseEntity<>("El producto se ha eliminado correctamente", HttpStatus.ACCEPTED); 
        } catch (Exception ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
