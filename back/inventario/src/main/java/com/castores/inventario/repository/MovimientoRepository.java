package com.castores.inventario.repository;

import com.castores.inventario.model.Movimiento;
import com.castores.inventario.dto.MovimientoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

	List<Movimiento> findByTipoMovimiento(String tipoMovimiento);

	@Query("""
			SELECT new com.castores.inventario.dto.MovimientoDTO(
				m.idMovimiento,
			    p.nombre,
			    u.nombre,
			    m.tipoMovimiento,
			    m.cantidad,
			    m.fechaHora
			)
			FROM Movimiento m
			JOIN m.producto p
			JOIN m.usuario u
			ORDER BY m.fechaHora DESC
			""")
			List<MovimientoDTO> obtenerHistorial();
}
