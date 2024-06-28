package application;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableRow;
/**
 * The class {@code Dashboard_Controller} is a controller for the dashboard.fxml
 */
public class Dashboard_Controller implements Initializable{
	@FXML
	private Label userLabel;
	
	@FXML
    private Button addBtn;

    @FXML
    private Button orderBtn;

    @FXML
    private AnchorPane addItemPane;

    @FXML
    private TableColumn<Item, Integer> addItems_IDcolumn;

    @FXML
    private TableColumn<Item, String> addItems_NameColumn;

    @FXML
    private TableColumn<Item, Double> addItems_PriceColumn;

    @FXML
    private TableColumn<Item, Integer> addItems_QuantityColumn;

    @FXML
    private TableColumn<Item, String> addItems_SupplierColumn;

    @FXML
    private TableView<Item> addItems_Table;

    @FXML
    private TableColumn<Item, String> addItems_TypeColumn;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane homePane;

    @FXML
    private TextField id;

    @FXML
    private AreaChart<Date, Double> incomeChart;

    @FXML
    private Label incomeLabel;

    @FXML
    private TextField name;

    @FXML
    private AreaChart<?, ?> ordersChart;

    @FXML
    private Label ordersLabel;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField search;

    @FXML
    private Label stockLabel;

    @FXML
    private ChoiceBox<String> supplier;

    @FXML
    private ChoiceBox<String> type;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private ChoiceBox<String> orderName;

    @FXML
    private AnchorPane orderPane;

    @FXML
    private Spinner<Integer> orderQuantity;

    @FXML
    private TextField orderSearch;

    @FXML
    private TextField orderID;
    
    @FXML
    private ChoiceBox<String> orderCustomer;

    @FXML
    private TableColumn<Item, Integer> order_IDcolumn;

    @FXML
    private TableColumn<Item, String> order_NameColumn;

    @FXML
    private TableColumn<Item, Double> order_PriceColumn;

    @FXML
    private TableColumn<Item, Integer> order_QuantityColumn;

    @FXML
    private TableColumn<Item, String> order_SupplierColumn;

    @FXML
    private TableView<Item> order_Table;

    @FXML
    private TableColumn<Item, String> order_TypeColumn;
    
    @FXML
    private Label totalLabel;
    
    @FXML
    private Label errorLabelOrder;
    
    @FXML
    private ListView<String> orderListView;
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private ObservableList<Item> itemList;
    private ArrayList<String> typeList;
    private ArrayList<String> supplierList;
    private ArrayList<String> nameList;
    private ArrayList<String> customerList;
	
    /**
     * Sets the {@code userLabel}
     * 
     * @param username
     */
	public void setUserLabel(String username) {
		userLabel.setText(username);
	}
	
	/**
	 * Sets an Arraylist based on the field parameter
	 * 
	 * @param field		used to select a column in the item table and the ArrayList to fill
	 */
	public void setArrayList(String field) {
		String query = "SELECT DISTINCT " + field + " FROM item";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (field.equals("type")) {
				typeList = new ArrayList<String>();
				while (rs.next()) {
					typeList.add(rs.getString("type"));
				}
			} else if (field.equals("supplier")) {
				supplierList = new ArrayList<String>();
				while (rs.next()) {
					supplierList.add(rs.getString("supplier"));
				}
			}
			else if (field.equals("name")) {
				nameList = new ArrayList<String>();
				while (rs.next()) {
					nameList.add(rs.getString("name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the {@code customerList} by reading the names from the {@code customer} table
	 */
	public void setCustomerList() {
		String query = "SELECT name FROM customer";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			customerList = new ArrayList<String>();
			while (rs.next()) {
				customerList.add(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs out the user to the login page
	 * 
	 * @param e the ActionEvent that triggered this method
	 */
	public void logout(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Are you sure?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			 try {
				Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
				Stage stage = new Stage();
				stage = (Stage) userLabel.getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Login");
				stage.show();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Switches Pane when one of the 3 buttons ({@code homeBtn, addBtn, orderBtn}) is presseda
	 * 
	 * @param e  the ActionEvent that triggered this method
	 */
	public void switchForm(ActionEvent e) {
		if(e.getSource() == homeBtn) {
			homePane.setVisible(true);
			addItemPane.setVisible(false);
			orderPane.setVisible(false);
			
			homeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b);");
			addBtn.setStyle("-fx-background-color:transparent");
			orderBtn.setStyle("-fx-background-color:transparent");
			
			setLowStockLabel();
			setIncomeLabel();
			setOrdersLabel();
			setIncomeChart();
			setOrdersChart();
		}
		else if (e.getSource() == addBtn) {
			homePane.setVisible(false);
			addItemPane.setVisible(true);
			orderPane.setVisible(false);
			
			homeBtn.setStyle("-fx-background-color:transparent");
			addBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b);");
			orderBtn.setStyle("-fx-background-color:transparent");
			
			updateItemListDataItem();
		}
		
		else if (e.getSource() == orderBtn) {
			homePane.setVisible(false);
			addItemPane.setVisible(false);
			orderPane.setVisible(true);

			homeBtn.setStyle("-fx-background-color:transparent");
			addBtn.setStyle("-fx-background-color:transparent");
			orderBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b);");

			updateItemListDataOrder();
		}
	}
	
	/**
	 * Fills an ObservableList with all the items from the {@code Database}
	 * 
	 * @return the ObservableList that has been filled
	 */
	public ObservableList<Item> getItemListData(){
		ObservableList<Item> itemList = FXCollections.observableArrayList();
		
		String query = "SELECT * FROM item";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				itemList.add(new Item(rs.getInt("id"), 
									  rs.getString("name"), 
									  rs.getString("type"), 
									  rs.getDouble("price"), 
									  rs.getInt("quantity"), 
									  rs.getString("supplier") ));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemList;
	}
	
	/**
	 * Updates the {@code addItems_Table} TableView and filters it by listening to the {@code search} TextField
	 */
	public void updateItemListDataItem() {
		itemList = getItemListData();
		
		addItems_IDcolumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
		addItems_NameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		addItems_TypeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		addItems_PriceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		addItems_QuantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
		addItems_SupplierColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("supplier"));
		
		addItems_Table.setItems(itemList);
		
		addItems_Table.setRowFactory(tv -> new TableRow<Item>() {
		    @Override
		    protected void updateItem(Item item, boolean empty) {
		        super.updateItem(item, empty);
		        if (item == null || empty) {
		            setStyle(""); 
		        } else {
		            if (item.getQuantity() < 10) {
		                setStyle("-fx-background-color: red;");
		            } else {
		                setStyle("");
		            }
		        }
		    }
		});
		
		FilteredList<Item> filteredData = new FilteredList<>(itemList, p -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
				if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getType().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getSupplier().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				else
					return false;
            });
        });
		
		SortedList<Item> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(addItems_Table.comparatorProperty());
		
		addItems_Table.setItems(sortedData);
	}
	
	/**
	 * Updates the {@code order_Table} TableView and filters it by listening to the {@code orderSearch} TextField
	 */
	public void updateItemListDataOrder() {
		itemList = getItemListData();

		order_IDcolumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
		order_NameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		order_TypeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
		order_PriceColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		order_QuantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
		order_SupplierColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("supplier"));

		order_Table.setItems(itemList);
		
		order_Table.setRowFactory(tv -> new TableRow<Item>() {
		    @Override
		    protected void updateItem(Item item, boolean empty) {
		        super.updateItem(item, empty);
		        if (item == null || empty) {
		            setStyle("");
		        } else {
		            if (item.getQuantity() < 10) {
		                setStyle("-fx-background-color: red;");
		            } else {
		                setStyle("");
		            }
		        }
		    }
		});
		
		FilteredList<Item> filteredData = new FilteredList<>(itemList, p -> true);

		orderSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getType().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getSupplier().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else
					return false;
			});
		});

		SortedList<Item> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(order_Table.comparatorProperty());

		order_Table.setItems(sortedData);
	}
	
	/**
	 * Checks if every fields is empty or invalid, then it adds the item to the {@code Database} and updates the table
	 * 
	 * @param e  the ActionEvent that triggered this method
	 */
	public void addItem(ActionEvent e) {
		
		if (id.getText().isEmpty() || name.getText().isEmpty() || type.getSelectionModel().isEmpty() || price.getText().isEmpty()
				|| quantity.getText().isEmpty() || supplier.getSelectionModel().isEmpty()) {
			errorLabel.setText("Some fields are empty!");
			return;
		}

		try {
			Integer.parseInt(id.getText());
			if (Integer.parseInt(id.getText()) < 0) {
				errorLabel.setText("ID must be a positive number!");
				return;
			}
		} catch (Exception e1) {
			errorLabel.setText("ID must be a number!");
			return;
		}
		
		try {
			Double.parseDouble(price.getText());
			if (Double.parseDouble(price.getText()) < 0) {
				errorLabel.setText("Price must be a positive number!");
				return;
			}
		} catch (Exception e1) {
			errorLabel.setText("Price must be a number!");
			return;
		}
		
		try {
			Integer.parseInt(quantity.getText());
			if (Integer.parseInt(quantity.getText()) < 0) {
				errorLabel.setText("Quantity must be a positive number!");
				return;
			}
		} catch (Exception e1) {
			errorLabel.setText("Quantity must be a number!");
			return;
		}
		
		if (name.getText().length() > 50) {
			errorLabel.setText("Name is too long!");
			return;
		}
		
		String queryCheck = "SELECT * FROM item WHERE id = ?";
		String query = "INSERT INTO item (id, name, type, price, quantity, supplier) VALUES (?, ?, ?, ?, ?, ?)";
		conn = Database.connectDb();

		try {
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
		    psCheck.setInt(1, Integer.parseInt(id.getText()));
		    rs = psCheck.executeQuery();
		    
		    if(rs.next())
		    	errorLabel.setText("ID already in use");
		    else {
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(id.getText()));
				ps.setString(2, name.getText());
				ps.setString(3, type.getSelectionModel().getSelectedItem().toString());
				ps.setDouble(4, Double.parseDouble(price.getText()));
				ps.setInt(5, Integer.parseInt(quantity.getText()));
				ps.setString(6, supplier.getSelectionModel().getSelectedItem().toString());
	
				ps.executeUpdate();
	
				updateItemListDataItem();
				clearItem();
				errorLabel.setText("");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Clears the TextFields and the choiceBoxes in the {@code addItemPane}
	 */
	public void clearItem() {
		id.clear();
		name.clear();
		type.setValue(null);
		price.clear();
		quantity.clear();
		supplier.setValue(null);
		errorLabel.setText("");
	}
	
	/**
	 * Checks if the id is correct or missing, then deletes the item from the {@code Database} and updates the table
	 * 
	 * @param e the ActionEvent that triggered this method
	 */
	public void deleteItem(ActionEvent e) {
		if (id.getText().isEmpty()) {
			errorLabel.setText("ID is empty!");
			return;
		}

		try {
			Integer.parseInt(id.getText());
		} catch (Exception e1) {
			errorLabel.setText("ID must be a number!");
			return;
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Item");
		alert.setHeaderText("You're about to delete an item!");
		alert.setContentText("Are you sure this is the right item?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			String query = "DELETE FROM item WHERE id = ?";
			conn = Database.connectDb();
	
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(id.getText()));
				int rowsAffected = ps.executeUpdate();
				
				if (rowsAffected > 0) {
					updateItemListDataItem();
					clearItem();
					errorLabel.setText("");
				} else {
					errorLabel.setText("Item not found!");
				}
	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Checks if if every fields is empty or invalid, then updates the item in the {@code Database} and updates the table
	 * 
	 * @param e the ActionEvent that triggered this method
	 */
	public void updateItem(ActionEvent e) {
		if (id.getText().isEmpty() || name.getText().isEmpty() || type.getSelectionModel().isEmpty() || price.getText().isEmpty()
                || quantity.getText().isEmpty() || supplier.getSelectionModel().isEmpty()) {
            errorLabel.setText("Some fields are empty!");
            return;
        }
		
		try {
			Integer.parseInt(id.getText());
			if (Integer.parseInt(id.getText()) < 0) {
				errorLabel.setText("ID must be a positive number!");
				return;
			}
		} catch (Exception e1) {
			errorLabel.setText("ID must be a number!");
			return;
		}
        
        try {
            Double.parseDouble(price.getText());
			if (Double.parseDouble(price.getText()) < 0) {
				errorLabel.setText("Price must be a positive number!");
				return;
			}
        } catch (Exception e1) {
            errorLabel.setText("Price must be a number!");
            return;
        }
        
        try {
            Integer.parseInt(quantity.getText());
            if (Integer.parseInt(quantity.getText()) < 0) {
				errorLabel.setText("Quantity must be a positive number!");
				return;
            }
        } catch (Exception e1) {
            errorLabel.setText("Quantity must be a number!");
            return;
        }
        
		if (name.getText().length() > 50) {
			errorLabel.setText("Name is too long!");
			return;
		}
		
        String query = "UPDATE item SET name = ?, type = ?, price = ?, quantity = ?, supplier = ? WHERE id = ?";
        conn = Database.connectDb();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, name.getText());
            ps.setString(2, type.getSelectionModel().getSelectedItem().toString());
            ps.setDouble(3, Double.parseDouble(price.getText()));
            ps.setInt(4, Integer.parseInt(quantity.getText()));
            ps.setString(5, supplier.getSelectionModel().getSelectedItem().toString());
            ps.setInt(6, Integer.parseInt(id.getText()));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
            	updateItemListDataItem();
                clearItem();
                errorLabel.setText("");
            } else {
                errorLabel.setText("Item not found!");
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
	
	/**
	 * When a row is clicked on the {@code addItems_Table}, the TextFields and ChoiceBoxes are set based on the row 
	 * 
	 * @param e the MouseEvent that triggered this method
	 */
	public void rowClickedItem(MouseEvent e){
		Item clickedItem = addItems_Table.getSelectionModel().getSelectedItem();
		
		try {
			id.setText(String.valueOf(clickedItem.getId()));
			name.setText(clickedItem.getName());
			type.setValue(clickedItem.getType());
			price.setText(String.valueOf(clickedItem.getPrice()));
			quantity.setText(String.valueOf(clickedItem.getQuantity()));
			supplier.setValue(clickedItem.getSupplier());
		} catch (NullPointerException e1) {}	
	}
	
	/**
	 * When a row is clicked on the {@code order_Table}, the TextField and ChoiceBox are set based on the row 
	 * 
	 * @param e the MouseEvent that triggered this method
	 */
	public void rowClickedOrder(MouseEvent e){
		Item clickedItem = order_Table.getSelectionModel().getSelectedItem();
		
		try {
			orderID.setText(String.valueOf(clickedItem.getId()));
			orderName.setValue(clickedItem.getName());
		} catch (NullPointerException e1) {}
	}
	
	/**
	 * Clears all the fields in the {@code orderPane}
	 */
	public void clearOrder() {
		orderID.clear();
        orderName.getItems().clear();
        orderName.getItems().addAll(nameList);
        orderCustomer.getItems().clear();
        orderCustomer.getItems().addAll(customerList);
        orderQuantity.getValueFactory().setValue(0);
        orderListView.getItems().clear();
        cartlist.clear();
        total = 0.0;
        errorLabelOrder.setText("");
        totalLabel.setText("$" + total);
    }
	
	private double total = 0;
	private ArrayList<Item> cartlist = new ArrayList<>();
	
	/**
	 * Adds an order to the ListView and the {@code cartList} based on the id, name and quantity
	 */
	public void addOrder() {
		if (orderID.getText().isEmpty() || orderName.getSelectionModel().isEmpty() || orderQuantity.getEditor().getText().isEmpty()) {
            errorLabelOrder.setText("Some fields are empty!");
            return;
        }
		if ( orderQuantity.getEditor().getText().equals("0")) {
			errorLabelOrder.setText("Quantity must be \ngreater than 0!");
			return;
		}
        
        try {
            Integer.parseInt(orderID.getText());
        } catch (Exception e1) {
            errorLabelOrder.setText("ID must be a number!");
            return;
        }
        
        String query = "SELECT * FROM item WHERE id = ?";
        conn = Database.connectDb();
        
        try {	
        	ps = conn.prepareStatement(query);
        	ps.setInt(1, Integer.parseInt(orderID.getText()));
        	rs = ps.executeQuery();
        	
        	if (rs.next()) {
        		if (Integer.parseInt(orderQuantity.getEditor().getText()) > rs.getInt("quantity")) {
					errorLabelOrder.setText("Quantity not available!");
					return;
        		}
        	    cartlist.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getDouble("price"), Integer.parseInt(orderQuantity.getEditor().getText()), rs.getString("supplier")));
        	    orderListView.getItems().add(orderQuantity.getEditor().getText() + " \t" + rs.getString("name") + " \t" + rs.getString("price"));
        	    total += Double.parseDouble(orderQuantity.getEditor().getText()) * rs.getDouble("price");
        	    total = Math.round(total * 100.0) / 100.0;
        		totalLabel.setText("$" + total);
        		errorLabelOrder.setText("");
			} else {
				errorLabelOrder.setText("Item not found!");
			}
        	
        }catch (Exception e1) {
            e1.printStackTrace();
        }
	}
	
	/**
	 * Removes the selected order from the ListView and the {@code cartList}
	 */
	public void removeOrder() {
		if (orderListView.getSelectionModel().isEmpty()) {
			errorLabelOrder.setText("Select an item \nto remove!");
			return;
		}

		String selectedItem = orderListView.getSelectionModel().getSelectedItem();
		String[] parts = selectedItem.split(" \t");
		double quantity = Double.parseDouble(parts[0]);
		double price = Double.parseDouble(parts[2]);
		
		cartlist.removeIf(item -> item.getName().equals(parts[1]));
		orderListView.getItems().remove(selectedItem);
		if (orderListView.getItems().isEmpty())
			total = 0.0;
		else
			total -= quantity * price;
		total = Math.round(total * 100.0) / 100.0;
		totalLabel.setText("$" + total);
		errorLabelOrder.setText("");
	}
	
	/**
	 * The order is executed and added to the {@code Database} in the orders table.
	 * It also updates the quantity of items affected
	 * Gives the choice to print a receipt on a .txt file
	 */
	public void saleOrder() {
        if (orderListView.getItems().isEmpty() || cartlist.isEmpty()) {
            errorLabelOrder.setText("Cart is empty!");
            return;
        }
        
        String query = "INSERT INTO orders (customer, total, type, date) VALUES (?, ?, ?, ?)";
        conn = Database.connectDb();
        
        try {
            ps = conn.prepareStatement(query);
            String selectedItem = orderCustomer.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                ps.setString(1, selectedItem);
            } else {
                ps.setNull(1, java.sql.Types.VARCHAR);
            }
            ps.setDouble(2, total = Math.round(total * 100.0) / 100.0);
            ps.setString(3, "Sale");
            ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
            
            for (Item item : cartlist) {
                String query2 = "UPDATE item SET quantity = quantity - ? WHERE id = ?";
                ps = conn.prepareStatement(query2);
                ps.setInt(1, item.getQuantity());
                ps.setInt(2, item.getId());
                ps.executeUpdate();
            }
            updateItemListDataOrder();
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sale");
            alert.setHeaderText("Sale Successful!");
            alert.setContentText("Would you like to print the receipt?");
			if (alert.showAndWait().get() == ButtonType.OK) {
				String fileName = "receipt_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
				FileWriter writer = new FileWriter("./src/application/" + fileName);
				selectedItem = orderCustomer.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					writer.write("Customer: " + selectedItem + "\n");
				}
				else {
					writer.write("Customer: null\n");
				}
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				writer.write("Date: " + date + "\n\n");
				writer.write("Items: \n");
				for (Item item : cartlist) {
					writer.write(item.getQuantity() + " \t" + item.getName() + " \t" + item.getPrice() + "\n");
				}
				writer.write("\nTotal: $" + total);
				writer.close();
			}
            
            clearOrder();
            total = 0.0;
            totalLabel.setText("$" + total);
            errorLabelOrder.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Set the stockLabel in the {@code homePane}
	 */
	public void setLowStockLabel() {
		String query = "SELECT COUNT(*) FROM item WHERE quantity < 10";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				stockLabel.setText(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the incomeLabel in the {@code homePane}
	 */
	public void setIncomeLabel() {
		String query = "SELECT SUM(total) FROM orders";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				incomeLabel.setText("$" +  Math.round(rs.getDouble(1) * 100.0) / 100.0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the ordersLabel in the {@code homePane}
	 */
	public void setOrdersLabel() {
		String query = "SELECT COUNT(*) FROM orders";
		conn = Database.connectDb();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				ordersLabel.setText(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the incomeChart in the {@code homePane}
	 */
	public void setIncomeChart() {
		incomeChart.getData().clear();
		XYChart.Series series = new XYChart.Series();
		
		String query = "SELECT date, SUM(total) FROM orders GROUP BY date";
		conn = Database.connectDb();
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				series.getData().add(new XYChart.Data(String.valueOf(rs.getDate(1)), rs.getDouble(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		incomeChart.getData().add(series);
	}
	
	/**
	 * Sets the ordersChart in the {@code homePane}
	 */
	public void setOrdersChart() {
		ordersChart.getData().clear();
		XYChart.Series series = new XYChart.Series();

		String query = "SELECT date, COUNT(*) FROM orders GROUP BY date";
		conn = Database.connectDb();

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				series.getData().add(new XYChart.Data(String.valueOf(rs.getDate(1)), rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ordersChart.getData().add(series);
	}
	
	/**
	 * Initializes all the TableView, choiceBoxes and Spinner
	 * It also sets all the Labels in the {@code homePane}
	 * 
	 * It listens to the {@code orderID} TextField and {@code orderName} ChoiceBox so that
	 * when on is set the other sets itself consequently
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		homeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b);");
		
		updateItemListDataItem();
		updateItemListDataOrder();
		
		setLowStockLabel();
		setIncomeLabel();
		setOrdersLabel();
		setIncomeChart();
		setOrdersChart();
		
		setArrayList("type");
		setArrayList("supplier");
		setArrayList("name");
		setCustomerList();
		type.getItems().addAll(typeList);
		supplier.getItems().addAll(supplierList);
		orderName.getItems().addAll(nameList);
		orderCustomer.getItems().addAll(customerList);
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		valueFactory.setValue(0);
		orderQuantity.setValueFactory(valueFactory);
		
		orderID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d+")) {
            	int id = Integer.parseInt(newValue);
            	String query = "SELECT name FROM item WHERE id = ?";
            	
            	conn = Database.connectDb();
            	
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, id);
					rs = ps.executeQuery();

					if (rs.next()) {
						orderName.setValue(rs.getString("name"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
		});
		
		orderName.valueProperty().addListener((observable, oldValue, newValue) -> {
			String query = "SELECT id FROM item WHERE name = ?";

			conn = Database.connectDb();

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, newValue);
				rs = ps.executeQuery();

				if (rs.next()) {
					orderID.setText(String.valueOf(rs.getInt("id")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		orderQuantity.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d+")) {
            	int quantity = Integer.parseInt(newValue);
            	valueFactory.setValue(quantity);
            }
		});
	}
}

