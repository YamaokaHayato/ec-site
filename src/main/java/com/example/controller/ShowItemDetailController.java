package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

@Controller
@RequestMapping("/")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@GetMapping("/showdetail")
	public String showDetail(Integer id, Model model) {
		Item item = showItemDetailService.detailByItem(id);
		model.addAttribute("item", item);
		System.out.println(item);
		return "item_detail";
	}
	
	

}
