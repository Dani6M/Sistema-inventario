package com.castores.inventario.controller;

import org.springframework.security.core.Authentication;

import com.castores.inventario.exception.BusinessException;
import com.castores.inventario.model.Usuario;
import com.castores.inventario.repository.UsuarioRepository;
import com.castores.inventario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;

	private final UsuarioRepository usuarioRepository;

	@PostMapping
	public Usuario guardar(@RequestBody Usuario usuario) {
		return usuarioService.guardar(usuario);
	}

	@GetMapping("/{id}")
	public Usuario buscar(@PathVariable Integer id) {
		return usuarioService.buscarPorId(id).orElse(null);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		usuarioService.eliminar(id);
	}

	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@GetMapping("/rol")
	public String obtenerRol(Authentication authentication) {
		return authentication.getAuthorities().iterator().next().getAuthority();
	}

	@GetMapping("/me")
	public Usuario obtenerUsuarioActual(Authentication authentication) {

		String correo = authentication.getName();

		return usuarioRepository.findByCorreo(correo).orElseThrow(() -> new BusinessException("Usuario no encontrado"));
	}

}