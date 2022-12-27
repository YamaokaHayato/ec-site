package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品一覧ページを処理するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 商品一覧ページの表示.
	 * 
	 * @param model    Model
	 * @param itemName 商品一覧
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/")
	public String showList(Model model, String name) {
		System.out.println("name: " + name);
		List<Item> itemList = showItemListService.serchByName(name);
		model.addAttribute("itemList", itemList);
		System.out.println(itemList);
		return "item_list";

	}

}
