package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.InsertUserForm;
import com.example.service.InsertUserService;

@Controller
@RequestMapping("/insertUser")
public class InsertUserController {
	
	@Autowired
	private InsertUserService insertUserService;
	
	@GetMapping("/toInsert")
	public String toInsert(InsertUserForm form) {
		return "register_admin";
	}
	
	@PostMapping("/insert")
	public String insert(@Validated InsertUserForm form, BindingResult result) {

		if(insertUserService.findByEmail(form.getEmail()) != null) {
			result.rejectValue("email", null, "そのメールアドレスは既に登録されています");
		}
		
		if(!form.getPassword().equals(form.getConfimationPassword())) {
			result.rejectValue("confimationPassword", null, "");
		}
		
		System.out.println(form.toString());
		if(result.hasErrors()) {
			return toInsert(form);
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setName(form.getFirstName() + form.getLastName());
		insertUserService.insert(user);
		return "redirect:/insertUser/login";
	}
	
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}

}
