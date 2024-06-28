package application;

/**
 * The {@code Item} class defines the item object
 */
public class Item {
	private int id;
	private String name;
	private String type;
	private double price;
	private int quantity;
	private String supplier;
	
	/**
	 * Class constructor
	 * 
	 * @param id  id of the item
	 * @param name  name of the item
	 * @param type  type of the item
	 * @param price  price of the item
	 * @param quantity  quantity of the item
	 * @param supplier  supplier of the item
	 */
	public Item(int id, String name, String type, double price, int quantity, String supplier) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.supplier = supplier;
	}
	
	/**
	 * Get the id
	 * 
	 * @return the id of the {@code Item}
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get the name
	 * 
	 * @return the name of the {@code Item}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the type
	 * 
	 * @return the type of the {@code Item}
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Get the price
	 * 
	 * @return the price of the {@code Item}
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Get the quantity
	 * 
	 * @return the quantity of the {@code Item}
	 */
	public int getQuantity() {
        return quantity;
    }
	
	/**
	 * Get the supplier
	 * 
	 * @return the supplier of the {@code Item}
	 */
	public String getSupplier() {
		return supplier;
	}
	
}
