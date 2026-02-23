package com.castores.inventario.service;

import com.castores.inventario.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

	Usuario guardar(Usuario usuario);

	void eliminar(Integer id);
	
	Optional<Usuario> buscarPorId(Integer id);
	
	List<Usuario> listar();
}
