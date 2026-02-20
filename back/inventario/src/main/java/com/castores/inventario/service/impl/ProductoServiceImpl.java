package com.castores.inventario.service.impl;

import com.castores.inventario.exception.BusinessException;
import com.castores.inventario.model.Movimiento;
import com.castores.inventario.model.Producto;
import com.castores.inventario.model.Usuario;

import com.castores.inventario.repository.MovimientoRepository;
import com.castores.inventario.repository.ProductoRepository;
import com.castores.inventario.repository.UsuarioRepository;

import com.castores.inventario.service.ProductoService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

	private final ProductoRepository productoRepository;
	private final MovimientoRepository movimientoRepository;
	private final UsuarioRepository usuarioRepository;

	@Override
	public List<Producto> listarTodos() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> listarActivos() {
		return productoRepository.findByEstatus("ACTIVO");
	}

	@Override
	public Producto guardar(Producto producto) {

	    if (producto.getCantidad() == null || producto.getCantidad() < 0) {
	        throw new BusinessException("Cantidad inválida");
	    }

	    producto.setEstatus("ACTIVO");
	    producto.setFechaCreacion(LocalDateTime.now());

	    Producto guardado = productoRepository.save(producto);

	    if (guardado.getCantidad() > 0) {

	        Usuario admin = usuarioRepository.findById(1)
	                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

	        Movimiento movimiento = Movimiento.builder()
	                .producto(guardado)
	                .usuario(admin)
	                .tipoMovimiento("ENTRADA")
	                .cantidad(guardado.getCantidad())
	                .fechaHora(LocalDateTime.now())
	                .build();

	        movimientoRepository.save(movimiento);
	    }

	    return guardado;
	}

	@Override
	public void darBaja(Integer id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Producto no encontrado"));
		producto.setEstatus("INACTIVO");
		productoRepository.save(producto);
	}

	@Override
	public void reactivar(Integer id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Producto no encontrado"));
		producto.setEstatus("ACTIVO");
		productoRepository.save(producto);
	}

	@Override
	public void entrada(Integer idProducto, Integer cantidad, Integer idUsuario) {

		if (cantidad <= 0)
			throw new BusinessException("La cantidad debe ser mayor a cero");

		Producto producto = productoRepository.findById(idProducto)
				.orElseThrow(() -> new BusinessException("Producto no encontrado"));

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new BusinessException("Usuario no encontrado"));

		producto.setCantidad(producto.getCantidad() + cantidad);
		productoRepository.save(producto);

		Movimiento movimiento = Movimiento.builder().producto(producto).usuario(usuario).tipoMovimiento("ENTRADA")
				.cantidad(cantidad).fechaHora(LocalDateTime.now()).build();

		movimientoRepository.save(movimiento);
	}

	@Override
	public void salida(Integer idProducto, Integer cantidad, Integer idUsuario) {

		if (cantidad <= 0)
			throw new BusinessException("La cantidad debe ser mayor a cero");

		Producto producto = productoRepository.findById(idProducto)
				.orElseThrow(() -> new BusinessException("Producto no encontrado"));

		if (!producto.getEstatus().equals("ACTIVO"))
			throw new BusinessException("El producto está inactivo");

		if (producto.getCantidad() < cantidad)
			throw new BusinessException("No hay suficiente inventario");

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new BusinessException("Usuario no encontrado"));

		producto.setCantidad(producto.getCantidad() - cantidad);
		productoRepository.save(producto);

		Movimiento movimiento = Movimiento.builder().producto(producto).usuario(usuario).tipoMovimiento("SALIDA")
				.cantidad(cantidad).fechaHora(LocalDateTime.now()).build();

		movimientoRepository.save(movimiento);
	}

}
