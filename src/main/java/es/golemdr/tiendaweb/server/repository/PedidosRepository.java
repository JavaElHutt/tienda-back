package es.golemdr.tiendaweb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tiendaweb.server.domain.Pedido;

	
@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long>{
	

}
