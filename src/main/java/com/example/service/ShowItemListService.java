package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品一覧情報を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 名前から商品を検索する.
	 * 
	 * @param name
	 * @return 商品情報リスト
	 */
	public List<Item> serchByName(String name) {
		if(name == null) {
			List<Item> itemList = itemRepository.findAll();
			return itemList;
		}
		List<Item> itemList = itemRepository.findbyName(name);
		return itemList;
	}

}
