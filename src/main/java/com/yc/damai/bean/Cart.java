package com.yc.damai.bean;

import java.util.List;

public class Cart {
	
	private Integer cartid;
	private Integer ciid;
	
	private List<Cartitem> cartitem;
	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", ciid=" + ciid + ", total=" + total + "]";
	}
	private double total;
	public Integer getCartid() {
		return cartid;
	}
	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<Cartitem> getCartitem() {
		return cartitem;
	}
	public void setCartitem(List<Cartitem> cartitem) {
		this.cartitem = cartitem;
	}
	
	

}
