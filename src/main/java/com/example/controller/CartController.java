package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.CartForm;
import com.example.service.CartService;

import jakarta.servlet.http.HttpSession;

/**
 * カート情報を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * カートの中身を表示する.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/showCart")
	public String showCart(Model model) {
		
		User user = (User) session.getAttribute("user");
		Integer userId = 0;
		if (user == null) {
			userId = session.hashCode();
		} else {
			userId = user.getId();
		}
		
		Order order = cartService.showCartList(userId); 
		if(order == null) {
			model.addAttribute("noOrder", "カート内は空です");
		} else {
			model.addAttribute("order", order);
		}
		return "cart_list";
	}
	
	/**
	 * 選択された注文商品を登録する.
	 * 
	 * @param form　CartForm
	 * @return　商品一覧ページ
	 */
	@PostMapping("/orderItemInsert")
	public String OrderItemInsert(CartForm form) {
		User user = (User) session.getAttribute("user");
		Integer userId = null;
		System.out.println(form);
		if (user == null) {
			userId = session.hashCode();
		} else {
			userId = user.getId();
		}
		cartService.addItem(form, userId);
		return "redirect:/cart/showCart";
	}
	
	/** 
	 * 選択されたOrderItemとそのOrderToppingを削除する.
	 * 
	 * @param orderItemId  選択されたorderitemId 
	 * @param toOrderConfirm
	 * @return 削除後のカート一覧画面
	 */
	@PostMapping("/deleteOrderItem")
	public String DeleteOrderItem(Integer orderItemId, String toOrderConfirm) {
		cartService.deleteOrderItem(orderItemId);
		if(toOrderConfirm.equals("toOrderConfirm")) {
			return "redirect:/order/toOrder";
		}
		return "redirect:/cart/showCart";
	}

}
