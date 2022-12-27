package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * ログアウト処理をするコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("logoutUser")
public class LogoutUserController {
	
	@Autowired
	private HttpSession session;

	/**
	 * ログアウトを行う.
	 * 
	 * @return　商品一覧画面
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginUser/toLogin";
	}

}
