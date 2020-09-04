package es.golemdr.tiendaweb.server.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.tiendaweb.server.domain.Categoria;
import es.golemdr.tiendaweb.server.domain.Producto;
import es.golemdr.tiendaweb.server.service.ProductosService;


@RestController
@RequestMapping("/api")
public class ProductosController {
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Autowired
	private ProductosService productosService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@GetMapping("/productos")
	public @ResponseBody List<Producto> listProductos() {
		
		List<Producto> productos = null;

		productos = productosService.getProductos();
		
		return productos;		
	}
	
	@GetMapping("/productos/categoria/{id}")
	public @ResponseBody List<Producto> getProductosPorCategoria(@PathVariable("id") Long idCategoria) {
		
		List<Producto> productos = null;
		
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		producto.setCategoria(categoria);
		
		Example<Producto> example = Example.of(producto);

		productos = productosService.getProductosPorCategoria(example);
		
		return productos;		
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> getProducto(@PathVariable("id") Long idProducto) {
		
		ResponseEntity<?> resultado = null;
		Producto producto = null;
		
		try {
			
			producto = productosService.getProductoById(idProducto);
			
			logger.info(objectMapper.writeValueAsString(producto));
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			logger.warning("No se encontró ningún producto con ID " + idProducto);
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> createProducto(@RequestBody Producto producto) throws JsonProcessingException {
		
		logger.info(objectMapper.writeValueAsString(producto));

		producto = productosService.insertarActualizarProducto(producto);

		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos")
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			logger.info(objectMapper.writeValueAsString(producto));
						
			producto = productosService.insertarActualizarProducto(producto);
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			logger.warning("No Constante found for ID " + producto.getIdProducto());
			resultado =  new ResponseEntity<String>("No Constante found for ID " + producto.getIdProducto(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable("id") Long idProducto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			productosService.borrarProducto(idProducto);
			
			resultado = new ResponseEntity<String>("El producto se borró correctamente", HttpStatus.OK);
			
		}catch (Exception e) {
			
			logger.warning("No se encontró ningún producto con ID " + idProducto);
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);
		}
		
		
		return resultado;
		

	}
}
