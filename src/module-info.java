module Inventory_Management {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base, javafx.controls;
}
