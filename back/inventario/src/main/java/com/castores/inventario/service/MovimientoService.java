package com.castores.inventario.service;

import com.castores.inventario.model.Movimiento;
import java.util.List;
import com.castores.inventario.dto.MovimientoDTO;

public interface MovimientoService {
	
	Movimiento guardarMovimiento(Movimiento movimiento);

    List<MovimientoDTO> listarMovimientos();

}
