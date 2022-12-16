package com.example.service;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class ShowOrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void showOrder(OrderForm orderForm) {
		Order order = orderRepository.load(Integer.parseInt(orderForm.getId()));
		
		// orederドメインにコピー
		BeanUtils.copyProperties(orderForm, order);
		//formとドメインの型が違うので手動でセット
		order.setDistinationTel(orderForm.getDestinationTel());
		order.setId(Integer.parseInt(orderForm.getId()));
		order.setPaymentMethod(Integer.parseInt(orderForm.getPaymentMethod()));
		LocalDate nowldate = LocalDate.now();
		order.setOrderDate(Date.valueOf(nowldate));
		//配達日時、時間を合わせてTimestamp型に変換
		LocalDate localDate = orderForm.getDeliveryDate().toLocalDate();
		LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), 
				localDate.getDayOfMonth(), orderForm.getDeliveryTime(), 0,0,0);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		order.setDeliveryTime(timestamp);
		
		if(orderForm.getPaymentMethod().equals("1")) {
			order.setStatus(1);
		} else if(orderForm.getPaymentMethod().equals("2")) {
			order.setStatus(2);
		}
		System.out.println("QQQQQorder = "+order);
		orderRepository.update(order);
	}

}
