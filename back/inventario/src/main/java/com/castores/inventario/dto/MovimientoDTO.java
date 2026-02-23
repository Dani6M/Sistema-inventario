package com.castores.inventario.dto;

import java.time.LocalDateTime;

public record MovimientoDTO(Integer idMovimiento,String producto, String usuario, String tipo, 
		Integer cantidad, LocalDateTime fecha) {

}
