package com.castores.inventario.service;

import com.castores.inventario.model.Producto;
import java.util.List;

public interface ProductoService {
	
	 List<Producto> listarTodos();

	    List<Producto> listarActivos();

	    Producto guardar(Producto producto);

	    void darBaja(Integer id);

	    void reactivar(Integer id);

	    void entrada(Integer idProducto, Integer cantidad, Integer idUsuario);

	    void salida(Integer idProducto, Integer cantidad, Integer idUsuario);

}
