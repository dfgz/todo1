package com.tienda.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.web.model.Marca;
import com.tienda.web.service.FacturaService;

@RestController
public class MarcasRestController {

	@Autowired
	private FacturaService facturaService;

	@GetMapping("/factura/listar")
	public List<Marca> listaEmpresas() {

		return facturaService.getMarcas();
	}

	
	
}
