package es.golemdr.tiendaweb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tiendaweb.server.domain.Cliente;

	
@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long>{
	

}
