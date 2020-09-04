package es.golemdr.tiendaweb.server.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "idDetalle", scope = Detalle.class)
@Entity
@Table(name="detalles")
public class Detalle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetalle;
	private Double precio;
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_producto")
	private Producto producto;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	

	public Long getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	

}
