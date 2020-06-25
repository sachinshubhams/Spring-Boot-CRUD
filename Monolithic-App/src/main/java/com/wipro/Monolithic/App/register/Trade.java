package com.wipro.Monolithic.App.register;

public class Trade {

	private String ticker;
	private double price;
	private int qty;
	private double total_cost;

	public Trade() {

	}

	public Trade(String ticker, double price, int qty, double total_cost) {
		super();
		this.ticker = ticker;
		this.price = price;
		this.qty = qty;
		this.total_cost = total_cost;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

}
