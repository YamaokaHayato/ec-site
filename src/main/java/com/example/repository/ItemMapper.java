package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Item;

/**
 * 商品情報を操作するマッパー
 * 
 * @author yamaokahayato
 *
 */
@Mapper
public interface ItemMapper {
	
	List<Item> findAll();

}
