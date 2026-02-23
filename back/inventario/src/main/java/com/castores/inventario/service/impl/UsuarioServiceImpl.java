package com.castores.inventario.service.impl;

import com.castores.inventario.repository.UsuarioRepository;
import com.castores.inventario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.castores.inventario.model.Usuario;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public Usuario guardar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void eliminar(Integer id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> buscarPorId(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
}
