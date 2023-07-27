package edu.escuelaing.arsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arsw.model.Comprador;
import edu.escuelaing.arsw.model.Usuario;
import edu.escuelaing.arsw.model.Vendedor;
import edu.escuelaing.arsw.services.CompradorService;
import edu.escuelaing.arsw.services.UserService;
import edu.escuelaing.arsw.services.VendedorService;

@RestController
@RequestMapping(value = "/SubastaExpress")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    CompradorService compradorService;

    @Autowired
    VendedorService vendedorService;

    /**
     * I want to create a new user, and if the user is a buyer, I want to create a new buyer, and if
     * the user is a seller, I want to create a new seller
     * 
     * @param user Usuario
     * @return The userService.newUser(usuario) is being returned.
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody Usuario user) {
        Usuario usuario = new Usuario(user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getSurname(),user.getRoles());

        String roles = user.getRoles();
        if (roles.equals(null)) {
            new RuntimeException("Error, no existe el rol");
        } else {
            user.setRoles(roles);
            if (roles.equals("Comprador")) {
                Comprador comprador = new Comprador(user.getUsername(), user.getSurname(), user.getEmail());
                compradorService.newComprador(comprador);
            }
            if (roles.equals("Vendedor")) {
                Vendedor vendedor = new Vendedor(user.getName(), user.getEmail());
                vendedorService.newVendedor(vendedor);
            }
        }
        System.out.println("Usuario Registrado");
        return new ResponseEntity<>(userService.newUser(usuario), HttpStatus.OK);
    }
    
    /**
     * It takes a username and password from the URL, and then checks if the username and password are
     * correct. If they are, it returns the user object. If they aren't, it returns an error message
     * 
     * @param username String
     * @param pwd 123456
     * @return The user object
     */
    @GetMapping("/login/{username}/{pwd}")
    public ResponseEntity<?> login(@RequestBody @PathVariable String username, @PathVariable String pwd) {
        Usuario user = userService.findByUserName(username);
        String nameUser = user.getUsername();
        String password = user.getPassword();
        if ((!nameUser.equals(username)) || (!password.equals(pwd))) {
            return new ResponseEntity<>("Usuario o contraseña incorrecta", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
