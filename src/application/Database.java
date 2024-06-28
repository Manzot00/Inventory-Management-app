package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The class {@code Database} provides methods to connect to a database, create a table, and insert data into the table.
 */
public class Database {
	private static final String DBURL = "jdbc:mysql://localhost:3306/inventory?";
	private static final String ARGS = "createDatabaseIfNotExist=true&serverTimezone=UTC";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	
	/**
	 * Connects to the database.
	 * 
	 * @return a connection to the database
	 */
	public static Connection connectDb() {
        try {
            Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * Creates the table {@code admin} in the database if it does not exist.
     */
	public static void createAdminTable() {
		try (Connection conn = connectDb();
				PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS admin (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))")) {
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	/**
	 * Inserts initial admin data into the {@code admin} table.
	 */
	public static void insertInitialAdminData() {
        try (Connection conn = connectDb();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO admin (username, password) VALUES (?, ?)")) {
            ps.setString(1, "admin1");
            ps.setString(2, "password1");
            ps.executeUpdate();

            ps.setString(1, "admin2");
            ps.setString(2, "password2");
            ps.executeUpdate();

            ps.setString(1, "admin3");
            ps.setString(2, "password3");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Checks if admin data exists in the {@code admin} table. If not, inserts initial admin data.
     */
	public static void adminDataExists() {
	    try (Connection conn = Database.connectDb();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM admin")) {

	        if (rs.next() && rs.getInt(1) > 0) {
	            return; 
	        } else {
	        	insertInitialAdminData();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Creates the table {@code Item} in the database if it does not exist.
	 */
	public static void createItemTable() {
		try (Connection conn = connectDb();
				PreparedStatement ps = conn.prepareStatement(
						"CREATE TABLE IF NOT EXISTS item (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), type VARCHAR(50), price DOUBLE, quantity INT, supplier VARCHAR(50))")) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts initial item data into the {@code item} table.
	 */
	public static void insertInitialItemData() {
		var items = List.of(
			new Item(1, "Smartphone", "Electronics", 499.99, 50, "TechCo"),
			new Item(2, "Running Shoes", "Apparel", 79.99, 100, "FootGear"),
			new Item(3, "Coffee Maker", "Appliances", 129.99, 30, "KitchenSupplies"),
			new Item(4, "Backpack", "Accessories", 39.99, 80, "OutdoorGear"),
		    new Item(5, "Wireless Earbuds", "Electronics", 89.99, 70, "AudioTech"),
		    new Item(6, "Yoga Mat", "Fitness", 29.99, 120, "YogaEmporium"),
		    new Item(7, "Gaming Mouse", "Gaming", 59.99, 40, "GamerZone"),
		    new Item(8, "Sunglasses", "Accessories", 99.99, 60, "EyeWear"),
		    new Item(9, "Cookware Set", "Kitchenware", 149.99, 20, "ChefSupply"),
		    new Item(10, "Smart Watch", "Wearable Tech", 199.99, 90, "TechGadgets"),
		    new Item(11, "Bluetooth Speaker", "Electronics", 129.99, 40, "AudioTech"),
		    new Item(12, "Dumbbell Set", "Fitness", 49.99, 60, "SportGear"),
		    new Item(13, "Desk Lamp", "Home Decor", 34.99, 80, "LightingInc"),
		    new Item(14, "Graphic T-Shirt", "Apparel", 19.99, 120, "FashionWear"),
		    new Item(15, "External Hard Drive", "Electronics", 89.99, 50, "DataTech"),
		    new Item(16, "Travel Backpack", "Accessories", 59.99, 70, "TravelEssentials"),
		    new Item(17, "Wireless Keyboard", "Electronics", 69.99, 30, "PCAccessories"),
		    new Item(18, "Resistance Bands", "Fitness", 14.99, 100, "GymSupplies"),
		    new Item(19, "Smart Thermostat", "Smart Home", 149.99, 40, "HomeTech"),
		    new Item(20, "Running Jacket", "Apparel", 69.99, 50, "AthleticWear"));
		
		try (Connection conn = connectDb();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO item (id, name, type, price, quantity, supplier) VALUES (?, ?, ?, ?, ?, ?)")) {
			for (Item i : items) {
				ps.setInt(1, i.getId());
				ps.setString(2, i.getName());
				ps.setString(3, i.getType());
				ps.setDouble(4, i.getPrice());
				ps.setInt(5, i.getQuantity());
				ps.setString(6, i.getSupplier());
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if item data exists in the {@code item} table. If not, inserts initial
	 * item data.
	 */
	public static void itemDataExists() {
		try (Connection conn = connectDb();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM item")) {
			if (rs.next() && rs.getInt(1) > 0) {
				return;
			} else {
				insertInitialItemData();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the table {@code orders} in the database if it does not exist.
	 */
	public static void createOrdersTable() {
        try (Connection conn = connectDb();
                PreparedStatement ps = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS orders (id INT AUTO_INCREMENT PRIMARY KEY, customer VARCHAR(50), total DOUBLE, type VARCHAR(50), date DATE)")) {
        	            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Inserts initial order data into the {@code orders} table.
	 */
	public static void insertInitialOrdersData() {
		var orders = List.of(
				new Order(1, null, 79.98, "Sale", new Date(124, 2, 17)),
                new Order(2, "Bob Johnson", 729.93, "Sale", new Date(124, 2, 17)),
                new Order(3, "Chris Martinez", 149.99, "Sale", new Date(124, 2, 17)),
                new Order(4, "Michelle Rodriguez", 314.96, "Sale", new Date(124, 2, 18)),
                new Order(5, "Sarah Wilson", 44.97, "Sale", new Date(124, 2, 18)),
                new Order(6, "Chris Martinez", 119.98, "Sale", new Date(124, 2, 20)),
                new Order(7, null, 89.99, "Sale", new Date(124, 2, 20)),
                new Order(8, null, 39.98, "Sale", new Date(124, 2, 20)),
                new Order(9, null, 149.99, "Sale", new Date(124, 2, 20)),
                new Order(10, "Jennifer Taylor", 969.96, "Sale", new Date(124, 2, 22))
				);
		
		try (Connection conn = connectDb();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO orders (id, customer, total, type, date) VALUES (?, ?, ?, ?, ?)")) {
			for (Order o : orders) {
				ps.setInt(1, o.getId());
				ps.setString(2, o.getCustomer());
				ps.setDouble(3, o.getTotal());
				ps.setString(4, o.getType());
				ps.setDate(5, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(o.getDate())));
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if order data exists in the {@code orders} table. If not, inserts
	 * initial order data.
	 */
	public static void ordersDataExists() {
		try (Connection conn = connectDb();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM orders")) {
			if (rs.next() && rs.getInt(1) > 0) {
				return;
			} else {
				insertInitialOrdersData();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the table {@code customer} in the database if it does not exist.
	 */
	public static void createCustomerTable() {
		try (Connection conn = connectDb();
                PreparedStatement ps = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS customer (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), phone_number VARCHAR(20), address VARCHAR(255))")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Inserts initial customer data into the {@code customer} table.
	 */
	public static void insertInitialCustomerData() {
		var customers = List.of(
			new Customer(1, "John Doe", "john.doe@example.com", "1234567890", "123 Main Street"),
			new Customer(2, "Alice Smith", "alice.smith@example.com", "9876543210", "456 Oak Avenue"),
			new Customer(3, "Bob Johnson", "bob.johnson@example.com", "5551234567", "789 Elm Street"),
			new Customer(4, "Emily Davis", "emily.davis@example.com", "9998887777", "321 Pine Avenue"),
			new Customer(5, "Michael Brown", "michael.brown@example.com", "4445556666", "567 Maple Street"),
        	new Customer(6, "Sarah Wilson", "sarah.wilson@example.com", "7778889999", "987 Cedar Avenue"),
        	new Customer(7, "David Jones", "david.jones@example.com", "3334445555", "654 Birch Street"),
        	new Customer(8, "Jennifer Taylor", "jennifer.taylor@example.com", "2223334444", "234 Spruce Avenue"),
        	new Customer(9, "Chris Martinez", "chris.martinez@example.com", "1112223333", "876 Willow Street"),
        	new Customer(10, "Michelle Rodriguez", "michelle.rodriguez@example.com", "6667778888", "543 Oak Lane"));
		
		try (Connection conn = connectDb();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO customer (id, name, email, phone_number, address) VALUES (?, ?, ?, ?, ?)")) {
			for (Customer c : customers) {
				ps.setInt(1, c.getId());
				ps.setString(2, c.getName());
				ps.setString(3, c.getEmail());
				ps.setString(4, c.getPhone_number());
				ps.setString(5, c.getAddress());
				ps.addBatch();
			}
			ps.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if customer data exists in the {@code customer} table. If not, inserts
	 * initial customer data.
	 */
	public static void customerDataExists() {
		try (Connection conn = connectDb();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM customer")) {
			if (rs.next() && rs.getInt(1) > 0) {
				return;
			} else {
				insertInitialCustomerData();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
