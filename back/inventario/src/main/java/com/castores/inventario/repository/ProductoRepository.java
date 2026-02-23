package com.castores.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.castores.inventario.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer>  {

	 List<Producto> findByEstatus(String estatus);
}
