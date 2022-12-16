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

@Controller
@RequestMapping("/loginUser")
public class LoginUserController {
	
	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/toLogin")
	public String toLogin(LoginUserForm form) {
		System.out.println("AAAAAAAAAA/loginnikimsdita");

		return "login";
	}
	
	@PostMapping("/login")
	public String login(LoginUserForm form, Model model) {
		System.out.println("/loginnikimsdita");
		User user = loginUserService.login(form.getEmail(), form.getPassword());
		if(user == null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが間違っています。");
			return toLogin(form);
		}
		session.setAttribute("user", user);
		System.out.println("qqqqqq");
		return "redirect:/";
	}

}
