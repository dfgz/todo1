package com.tienda.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tienda.web.dao.FacturaDao;
import com.tienda.web.model.Detalle;
import com.tienda.web.model.Factura;
import com.tienda.web.model.Marca;
import com.tienda.web.model.Stock;

@Repository
public class FacturaDaoImpl implements FacturaDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Marca> getMarcas() {

		String sql = "select mar_codigo, mar_nombre from marca ";

		System.out.println(sql);

		return jdbcTemplate.query(sql, new RowMapper<Marca>() {

			@Override
			public Marca mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Marca m = new Marca();

				m.setCodigo(rs.getInt("mar_codigo"));
				m.setNombre(rs.getString("mar_nombre"));

				return m;

			}

		});

	}

	@Override
	public List<Factura> getFactura(int codigo) {

		String sql = "select fac_codigo, fac_fecha, fac_total, cli_id, cli_nombre, cli_apellido "
				+ "from factura f, cliente c " + "where f.cli_codigo = c.cli_codigo " + "and fac_codigo = ? ";

		System.out.println(sql);
		System.out.println(codigo);

		return jdbcTemplate.query(sql, new Object[] { codigo }, new RowMapper<Factura>() {

			@Override
			public Factura mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Factura f = new Factura();

				f.setCodigo(rs.getInt("fac_codigo"));
				f.setFecha(rs.getDate("fac_fecha"));
				f.setTotal(rs.getFloat("fac_total"));
				f.setClienteId(rs.getInt("cli_id"));
				f.setNombre(rs.getString("cli_nombre"));
				f.setApellido(rs.getString("cli_apellido"));

				return f;

			}

		});

	}

	@Override
	public List<Detalle> getDetalle(int codigo) {

		String sql = "select det_item, fac_codigo, det_cantidad, det_precio, "
				+ "		inv_stock, pro_descripcion, tip_nombre, mar_nombre, i.inv_codigo "
				+ "		from detalle d, inventario i, producto p, tipo t, marca m "
				+ "		where i.inv_codigo = d.inv_codigo and i.pro_codigo = p.pro_codigo "
				+ "		and p.tip_codigo = t.tip_codigo and p.mar_codigo = m.mar_codigo "
				+ "		and d.fac_codigo = ? ";

		System.out.println(sql);
		System.out.println(codigo);

		return jdbcTemplate.query(sql, new Object[] { codigo }, new RowMapper<Detalle>() {

			@Override
			public Detalle mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Detalle d = new Detalle();

				d.setItem(rs.getInt("det_item"));
				d.setFactura(rs.getInt("fac_codigo"));
				d.setDescripcion(rs.getString("pro_descripcion"));
				d.setMarca(rs.getString("mar_nombre"));
				d.setTipo(rs.getString("tip_nombre"));
				d.setCantidad(rs.getInt("det_cantidad"));
				d.setStock(rs.getInt("inv_stock"));
				d.setPrecio(rs.getInt("det_precio"));
				d.setInv(rs.getInt("inv_codigo"));

				return d;

			}

		});

	}

	@Override
	public List<Stock> getStock(String buscar) {

		buscar = buscar.toUpperCase();

		String sql = "select p.pro_codigo, inv_codigo, inv_precio, pro_descripcion, disponible as stock, "
				+ "mar_nombre, tip_nombre " + "from stock s, producto p, marca m, tipo t "
				+ "where s.pro_codigo = p.pro_codigo " + "and p.mar_codigo = m.mar_codigo "
				+ "and p.tip_codigo = t.tip_codigo " + "and (Upper(pro_descripcion) like :lk "
				+ "or Upper(mar_nombre) like :lk " + "or Upper(tip_nombre) like :lk)";

		System.out.println(sql);
		System.out.println(buscar);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("lk", "%" + buscar + "%");

		return namedParameterJdbcTemplate.query(sql, params, new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Stock st = new Stock();

				st.setCodigo(rs.getInt("pro_codigo"));
				st.setDescripcion(rs.getString("pro_descripcion"));
				st.setMarca(rs.getString("mar_nombre"));
				st.setTipo(rs.getString("tip_nombre"));
				st.setStock(rs.getInt("stock"));
				st.setInv(rs.getInt("inv_codigo"));
				st.setPrecio(rs.getInt("inv_precio"));
				;
				return st;

			}

		});
	}

	@Override
	public int addDetalle( int inv, int pre, int can, int fac) {
		
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("var1",inv); 
		params.put("var2",pre);
		params.put("var3",can);
		params.put("var4",fac); 
		
		String sql = "insert into detalle (inv_codigo, det_precio, det_cantidad, fac_codigo) " + 
				"values(:var1, :var2, :var3, :var4 )";
		
		return namedParameterJdbcTemplate.update(sql, params);
		 
 
	
 
	}

	@Override
	public int deleteDetalle(int fac, int inv) {
		String sql = "delete from detalle where fac_codigo= ? and inv_codigo= ?";
		
		System.out.println(sql); 
		System.out.println("fac:"+fac + ", inv:"+ inv);
		
		return jdbcTemplate.update(sql, fac, inv);
		
		 
	}

}
