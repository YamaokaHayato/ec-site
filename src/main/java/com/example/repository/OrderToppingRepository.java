package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

@Repository
public class OrderToppingRepository {
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = (rs, i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setToppingId(rs.getInt("toppingId"));
		orderTopping.setOrderItemId(rs.getInt("orderItemId"));
		return orderTopping;
	};
	
	public void insert(OrderTopping orderTopping) {
		
		System.out.println("orderTopping = "+orderTopping);
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		String sql = "INSERT INTO order_toppings (topping_id,order_item_id) VALUES (:toppingId, :orderItemId)";
		template.update(sql, param); 
	}
	
	public void deleteById(Integer orderItemId) {
		String sql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);
	}

}
