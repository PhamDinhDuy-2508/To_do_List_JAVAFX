package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		System.out.println("main check "); 
		Parent root_pathParent =  FXMLLoader.load(getClass().getResource("Main_XML.fxml")) ; 

			Scene scene = new Scene(root_pathParent,700,500); 
			primaryStage.setScene(scene);
			primaryStage.setTitle("TODOLIST");
			
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) { 
				
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		
		
		try {
			System.out.println();
			Save_List.get_Save_List().Store_List();
		} 
		catch (IOException e) { 
		
		} 
		try {
			Class.forName("org.sqlite.JDBC");


					
		} catch (Exception e) { 
			e.printStackTrace() ; 
		}
	}
}
