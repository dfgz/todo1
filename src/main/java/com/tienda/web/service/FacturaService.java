package com.tienda.web.service;

import java.util.List;

import com.tienda.web.model.Factura;
import com.tienda.web.model.Factura;
import com.tienda.web.model.Marca;

public interface FacturaService {

	public List<Marca> getMarcas();
	public List<Factura> getFactura(int codigo);
}
