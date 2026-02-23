package com.castores.inventario.controller;

import com.castores.inventario.model.Producto;
import com.castores.inventario.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
	
	private final ProductoService productoService;

    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    @GetMapping("/activos")
    public List<Producto> listarActivos() {
        return productoService.listarActivos();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }
    
    @PutMapping("/baja/{id}")
    public void darBaja(@PathVariable Integer id) {
        productoService.darBaja(id);
    }
    

    @PutMapping("/reactivar/{id}")
    public void reactivar(@PathVariable Integer id) {
        productoService.reactivar(id);
        
    }

    @PostMapping("/entrada")
    public void entrada(@RequestParam Integer idProducto,
                        @RequestParam Integer cantidad,
                        @RequestParam Integer idUsuario) {
        productoService.entrada(idProducto, cantidad, idUsuario);
    }

    @PostMapping("/salida")
    public void salida(@RequestParam Integer idProducto,
                       @RequestParam Integer cantidad,
                       @RequestParam Integer idUsuario) {
        productoService.salida(idProducto, cantidad, idUsuario);
    }

}
