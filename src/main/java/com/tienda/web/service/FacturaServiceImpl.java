package com.tienda.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.web.dao.FacturaDao;
import com.tienda.web.model.Factura;
import com.tienda.web.model.Marca;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDao facturaDao;

	@Override
	public List<Marca> getMarcas() {

		return facturaDao.getMarcas();
	}

	@Override
	public List<Factura> getFactura(int codigo) {
		 
		return facturaDao.getFactura(codigo);
	};

}
