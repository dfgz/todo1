package com.tienda.web.dao;
 
import java.util.List;

import com.tienda.web.model.Detalle;
import com.tienda.web.model.Factura;
import com.tienda.web.model.Marca;
import com.tienda.web.model.Stock;

public interface FacturaDao {

	public List<Marca> getMarcas();
	
	public List<Factura> getFactura(int codigo);
	public List<Detalle> getDetalle(int codigo);
	public List<Stock> getStock(String buscar);
	public int addDetalle( int inv, int pre, int can, int fac);
	public int deleteDetalle(int fac, int inv);
	
}
