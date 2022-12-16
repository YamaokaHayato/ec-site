package com.example.form;

import java.util.List;

/**
 * カートに登録時のform.
 * 
 * @author yamaokahayato
 *
 */
public class CartForm {

	private Integer ItemId;
	private String size;
	private Integer quantity;
	private List<Integer> toppingList;

	@Override
	public String toString() {
		return "CartForm [ItemId=" + ItemId + ", size=" + size + ", quantity=" + quantity + ", toppingList="
				+ toppingList + "]";
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

}
