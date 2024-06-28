package application;

/**
 * The {@code Customer} class defines the customer object
 */
public class Customer {
	private int id;
	private String name;
	private String email;
	private String phone_number;
	private String address;
	
	/**
	 * Class constructor
	 * 
	 * @param id	id of the customer
	 * @param name	name of the customer
	 * @param email	email of the customer
	 * @param phone_number	phone_number of the customer
	 * @param address	address of the customer
	 */
	public Customer(int id, String name, String email, String phone_number, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
	}
	
	/**
	 * Get the id
	 * 
	 * @return the id of the {@code Customer}
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get the name
	 * 
	 * @return the name of the {@code Customer}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the email
	 * 
	 * @return the email of the {@code Customer}
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Get the phone_number
	 * 
	 * @return the phone_number of the {@code Customer}
	 */
	public String getPhone_number() {
		return phone_number;
	}
	
	/**
	 * Get the address
	 * 
	 * @return the address of the {@code Customer}
	 */
	public String getAddress() {
		return address;
	}
	
}
