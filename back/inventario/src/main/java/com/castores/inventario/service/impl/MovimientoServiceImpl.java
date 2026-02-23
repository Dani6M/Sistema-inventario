package com.castores.inventario.service.impl;

import com.castores.inventario.dto.MovimientoDTO;
import com.castores.inventario.model.Movimiento;
import com.castores.inventario.repository.MovimientoRepository;
import com.castores.inventario.service.MovimientoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService{
	
	private final MovimientoRepository movimientoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public Movimiento guardarMovimiento(Movimiento movimiento) {
        movimiento.setFechaHora(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }
    
    @Override
    public List<MovimientoDTO> listarMovimientos() {
        return movimientoRepository.obtenerHistorial();
    }
    
}
