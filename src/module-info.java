module app_test {
	requires javafx.controls;
	requires javafx.fxml ;
	requires java.sql ; 
	opens application to javafx.graphics, javafx.fxml;
}
