package application;

import java.util.Date;

/**
 * The class {@code Order} represents an order.
 * 
 */
public class Order {
	int id;
	String customer;
	double total;
	String type;
	Date date;
	
	/**
	 * Constructs an {@code Order} object with the specified id, customer, total,
	 * type, and date.
	 * 
	 * @param id       the id of the order
	 * @param customer the customer who placed the order
	 * @param total    the total cost of the order
	 * @param type     the type of the order
	 * @param date     the date the order was placed
	 */
	public Order(int id, String customer, double total, String type, Date date) {
		this.id = id;
		this.customer = customer;
		this.total = total;
		this.type = type;
		this.date = date;
	}
	
	/**
	 * Returns the id of the order.
	 * 
	 * @return the id of the order
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the customer who placed the order.
	 * 
	 * @return the customer who placed the order
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * Returns the total cost of the order.
	 * 
	 * @return the total cost of the order
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * Returns the type of the order.
	 * 
	 * @return the type of the order
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns the date the order was placed.
	 * 
	 * @return the date the order was placed
	 */
	public Date getDate() {
		return date;
	}
}

