package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

/**
 * 商品詳細ページを処理するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	/**
	 * 商品一覧ページを表示.
	 * 
	 * @param id　商品ID  
	 * @param model Model
	 * @return 商品詳細ページ
	 */
	@GetMapping("/showdetail")
	public String showDetail(Integer id, Model model) {
		Item item = showItemDetailService.detailByItem(id);
		model.addAttribute("item", item);
		return "item_detail";
	}
	
	

}
