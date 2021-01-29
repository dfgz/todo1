package com.tienda.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.web.dao.FacturaDao;
import com.tienda.web.model.Detalle;
import com.tienda.web.model.Factura;
import com.tienda.web.model.Stock;

@RestController
public class FacturaRestController {

	@Autowired
	private FacturaDao facturaDao;
	
	
	@PostMapping("/factura")
	public List<Factura> factura(
			@RequestParam(name="codigo") int codigo 
			){
		
		return facturaDao.getFactura(codigo);
		
	}
	
	@PostMapping("/detalle")
	public List<Detalle> detalle(
			@RequestParam(name="codigo") int codigo
			){
		
		return facturaDao.getDetalle(codigo);
		
	}
	 

	@PostMapping("/stock")
	public List<Stock> stock(
			@RequestParam(name="buscar") String buscar
			){
		
		return facturaDao.getStock(buscar);
		
	}
	
	@PostMapping("/insertarDetalle")
	public int insertarDetalle(
			@RequestParam(name="inv") int inv,
			@RequestParam(name="precio") int precio,
			@RequestParam(name="cantidad") int cantidad,
			@RequestParam(name="fac") int fac
			){
		
		return facturaDao.addDetalle(inv, precio, cantidad, fac);
		
	}

	@PostMapping("/borrarDetalle")
	public int borrarDetalle(
			@RequestParam(name="inv") int inv, 
			@RequestParam(name="fac") int fac
			){
		
		return facturaDao.deleteDetalle(fac, inv);
		
	}
	
}
