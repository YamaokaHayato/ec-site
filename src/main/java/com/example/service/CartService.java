package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.form.CartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private HttpSession session;
	
	public Order showCartList(Integer useId) {
		Order existorder = orderRepository.findByUserIdAndStatus(useId, 0);
		if(existorder == null) {
			return null;
		}
		Order order = orderRepository.load(existorder.getId());
		return order;
	}
	
	public void addItem(CartForm form, Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId,0);
		
		if (order == null) {
			Order createOrder = new Order();
			createOrder.setUserId(userId);
			createOrder.setStatus(0);
			createOrder.setTotalPrice(0);
			orderRepository.insert(createOrder);
		}
		
		order = orderRepository.findByUserIdAndStatus(userId, 0);
		Integer orderId = order.getId();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getItemId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(form.getQuantity());
		orderItem.setSize(form.getSize());
		System.out.println(form);
		orderItemRepository.insert(orderItem);
		
		OrderTopping orderTopping = new OrderTopping();
		List<Integer> toppingList = form.getToppingList();
		
		
		if (toppingList != null) {
			for (Integer toppingId : toppingList) {
				orderTopping.setToppingId(toppingId);
				//自動採番されたorderIDを取得
				OrderItem oi = orderItemRepository.findMaxId();
				Integer recentId = oi.getId();
				orderTopping.setOrderItemId(recentId);
				Topping topping = toppingRepository.load(toppingId);
				orderTopping.setTopping(topping);
				orderToppingRepository.insert(orderTopping);
			}
		}
	}
	
	public void deleteOrderItem(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
		orderToppingRepository.deleteById(orderItemId);
	}
	
	

}
