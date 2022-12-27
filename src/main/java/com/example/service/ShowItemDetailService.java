package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品詳細画面を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * IDから商品情報を検索する
	 * 
	 * @param id　商品ID
	 * @return　商品情報
	 */
	public Item detailByItem(Integer id) {
		Item item = itemRepository.load(id);
		List<Topping> toppingList = toppingRepository.findAll();
		item.setToppingList(toppingList);
		return item;
	}
	
}
