package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン画面の処理を行うコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/loginUser")
public class LoginUserController {
	
	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ログイン画面に遷移.
	 * 
	 * @param form LoginUserForm
	 * @return ログイン画面
	 */
	@GetMapping("/toLogin")
	public String toLogin(LoginUserForm form) {
		return "login";
	}
	
	/**
	 * ログインを行う.
	 * 
	 * @param form LoginUserForm
	 * @param model Model
	 * @return
	 */
	@PostMapping("/login")
	public String login(LoginUserForm form, Model model) {
		User user = loginUserService.login(form.getEmail(), form.getPassword());
		if(user == null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが間違っています。");
			return toLogin(form);
		}
		session.setAttribute("user", user);
		return "redirect:/";
	}

}
