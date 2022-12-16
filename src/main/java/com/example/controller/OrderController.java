package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.CartService;
import com.example.service.ShowOrderService;

import jakarta.servlet.http.HttpSession;

/**
 * 注文情報を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ShowOrderService showOrderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private HttpSession session;
	
	/**
	 * 注文画面の表示.
	 * 
	 * @param orderForm
	 * @return
	 */
	@GetMapping("/toOrder")
	public String toOrder(OrderForm orderForm, Model model) {
		User user = null;
		user = (User) session.getAttribute("user");
		Integer userId = 0;
		try {
			userId = user.getId();
		} catch (Exception e) {
			session.setAttribute("throughOrderConfimation", true);
			return "redirect:/loginUser/toLogin";
		}
		
		Order order = cartService.showCartList(userId);
		model.addAttribute("order", order);
		
		return "order_confirm";
	}
	
	@PostMapping("/autoEntry")
	public String destination(OrderForm orderForm, Model model) {
		model.addAttribute("autoEntry", "autoEntry");
		return toOrder(orderForm, model);
	}
	
	@PostMapping("/comfirmedOrder")
	public String comfirmedOrder(@Validated OrderForm orderForm, BindingResult result, Model model) {
		if (orderForm.getDeliveryDate() == null) {
			model.addAttribute("deliveryDateError", "日付を入力してください");
			return toOrder(orderForm, model);
		}
		LocalDateTime nowDateTime = LocalDateTime.now();
		nowDateTime = nowDateTime.plusHours(3);
		LocalDateTime orderDateTime = orderForm.getDeliveryDate().toLocalDate().atStartOfDay();
		orderDateTime.plusHours(orderForm.getDeliveryTime());
		//この日時が、指定された日時より後にあるをisAfterでチェックします
		if(nowDateTime.isAfter(orderDateTime)) {
			model.addAttribute("deliveryDateError", "今から3時間後の日時を入力してください");
			return toOrder(orderForm, model);
		}
		
		if(result.hasErrors()) {
			return toOrder(orderForm, model);
		}
		System.out.println(orderForm);
		showOrderService.showOrder(orderForm);
		return "redirect:/order/toFinished";
	}
	
	@GetMapping("/toFinished")
	public String toFinished() {
		return "order_finished";
	}
}
