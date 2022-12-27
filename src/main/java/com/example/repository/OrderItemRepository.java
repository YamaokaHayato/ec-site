package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * OrderItemを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("itemId"));
		orderItem.setOrderId(rs.getInt("orderId"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size"));
		return orderItem;
	};
	
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER2 = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		return orderItem;
	};
	
	/**
	 * オーダーアイテム情報を追加する.
	 * 
	 * @param orderItem
	 * @return
	 */
	public void insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id,order_id,quantity,size) VALUES (:itemId, :orderId, :quantity, :size) ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
	}
	
	/**
	 * 登録した最新のOrderItemを1件取り出す.
	 * 
	 * @return orderItem
	 */
	public OrderItem findMaxId() {
		String sql = "SELECT max(id) id FROM order_items;";
		List<OrderItem> orderItemList = template.query(sql, ORDER_ITEM_ROW_MAPPER2);
		return orderItemList.get(0);
	}
	
	/**
	 * トッピングを含めた注文情報を1件削除する
	 * 
	 * @param orderItemId 注文情報ID
	 */
	public void deleteById(Integer orderItemId) {
		String sql = "DELETE FROM order_items WHERE id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);
	}

}