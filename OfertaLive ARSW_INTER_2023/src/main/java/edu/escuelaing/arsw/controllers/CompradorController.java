package edu.escuelaing.arsw.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arsw.model.Comprador;
import edu.escuelaing.arsw.model.Producto;
import edu.escuelaing.arsw.model.Vendedor;
import edu.escuelaing.arsw.services.CompradorService;
import edu.escuelaing.arsw.services.ProductoService;
import edu.escuelaing.arsw.services.VendedorService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/Comprador")
@CrossOrigin("*")
public class CompradorController {
    
    @Autowired
    CompradorService compradorService;

    @Autowired
    ProductoService productoService;

    @Autowired
    VendedorService vendedorService;

    /**
     * It returns a list of products from the database
     * 
     * @return A list of products
     */
    @GetMapping("/productos")
    public ResponseEntity<?> getProducts() {
        List<Producto> productos = productoService.findALLProducts();
        return new ResponseEntity<>(productos, HttpStatus.ACCEPTED);
    }

    /**
     * It returns a product by id
     * 
     * @param id Long
     * @return A ResponseEntity object.
     */
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductsById(@PathVariable Long id) {
        try{
            Producto producto = productoService.productoById(id);
            return new ResponseEntity<>(producto, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * It takes a string as a parameter, and returns a producto object
     * 
     * @param name The name of the product
     * @return A producto object
     */
    @GetMapping("/productos/listar/{name}")
    public ResponseEntity<?> getProductsbyName(@PathVariable String name) {
        Producto producto = productoService.findByName(name);
        return new ResponseEntity<>(producto, HttpStatus.ACCEPTED);
    }
    
    /**
     * It returns a list of products that have a price equal to the price passed as a parameter
     * 
     * @param precio Double
     * @return A list of products with the same price.
     */
    @GetMapping("/productos/buscar/{precio}")
    public ResponseEntity<?> getProductsbyPrize(@PathVariable Double precio) {
        List<Producto> productosPrecio = productoService.findByprize(precio);
        return new ResponseEntity<>(productosPrecio, HttpStatus.ACCEPTED);
    }

    /**
     * It takes a name as a parameter, finds the comprador with that name, and returns it
     * 
     * @param name The name of the comprador you want to retrieve.
     * @return A ResponseEntity object.
     */
    @GetMapping("/comprador/{name}")
    public ResponseEntity<?> getcompradorByName(@PathVariable String name) {
        Comprador comprador = compradorService.findByName(name);
        return new ResponseEntity<>(comprador, HttpStatus.ACCEPTED);
    }

    /**
     * It returns a Vendedor object with the id that was passed as a parameter
     * 
     * @param id The id of the vendedor you want to retrieve.
     * @return A ResponseEntity object.
     */
    @GetMapping("/vendedor/{id}")
    public ResponseEntity<?> getVendedorById(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.findById(id);
        return new ResponseEntity<>(vendedor, HttpStatus.ACCEPTED);
    }

    /**
     * It returns a list of all the vendedores in the database
     * 
     * @return A list of Vendedor objects.
     */
    @GetMapping("/vendedor")
    public ResponseEntity<?> getAllVendedores() {
        List<Vendedor> vendedores = vendedorService.findAll();
        return new ResponseEntity<>(vendedores, HttpStatus.ACCEPTED);
    }

    /**
     * It returns a list of products that belong to a seller
     * 
     * @param id the id of the seller
     * @return A list of products
     */
    @GetMapping("/comprador/vendedor/{id}")
    public ResponseEntity<?> getProductosDelVendedor(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.findById(id);
        return new ResponseEntity<>(productoService.findByVendedor(vendedor), HttpStatus.ACCEPTED);
    }

}
