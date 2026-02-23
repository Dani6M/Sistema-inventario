package com.castores.inventario.controller;

import com.castores.inventario.dto.MovimientoDTO;
import com.castores.inventario.model.Movimiento;
import com.castores.inventario.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor

public class MovimientoController {
	
	private final MovimientoRepository movimientoRepository;

	@GetMapping
	public List<MovimientoDTO> listar() {
	    return movimientoRepository.obtenerHistorial();
	}
    @GetMapping("/tipo/{tipo}")
    public List<Movimiento> filtrarPorTipo(@PathVariable String tipo) {
        return movimientoRepository.findByTipoMovimiento(tipo);
    }

}
