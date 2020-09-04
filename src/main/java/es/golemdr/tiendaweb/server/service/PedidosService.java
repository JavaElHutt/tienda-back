package es.golemdr.tiendaweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.golemdr.tiendaweb.server.domain.Pedido;
import es.golemdr.tiendaweb.server.repository.PedidosRepository;

@Service
public class PedidosService {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	public List<Pedido> getPedidos(){
		
		return pedidosRepository.findAll();
		
	}
	
	public Pedido getPedidoById(Long idPedido){
		
		return pedidosRepository.findById(idPedido).get();
		
	}
	
	public Pedido insertarActualizarPedido(Pedido pedido) {
		
		return pedidosRepository.save(pedido);
		
	}
	
	public void borrarPedido(Long idPedido) {
		
		pedidosRepository.deleteById(idPedido);
		
	}
	

}
