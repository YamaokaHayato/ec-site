package com.example.domain;

import java.util.List;

/**
 * 商品情報を表すドメイン.
 * 
 * @author yamaokahayato
 *
 */
public class Item {
	
	/* ID */
	private Integer id;
	/* 商品名 */
	private String name;
	/* 説明 */
	private String description;
	/* Mの価格 */
	private Integer priceM;
	/* Lの価格 */
	private Integer priceL;
	/* 画像パス */
	private String imagePath;
	/* トッピングリスト */
	private List<Topping> toppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath +  ", toppingList=" + toppingList + "]";
	}
	
}
