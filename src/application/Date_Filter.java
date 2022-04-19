package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
public class Date_Filter   { 
	private ArrayList<Compoment> arrayList  ;


	public Date_Filter(ArrayList<Compoment> arrayList) {
		super();
		this.arrayList = arrayList;
	}

	public void set_Array_Compoment(ArrayList<Compoment> arrayList) { 
		this.arrayList =  arrayList ; 	
	} 
	
	public ArrayList<Compoment> Get_Compoment() {
		// TODO Auto-generated method stub
		return this.arrayList ; 
	}
	
	
	
//	public void Show_dialog( )   {   
//		 
//		System.out.println("TITLE");
//	Dialog<ButtonType> dialog  =  new Dialog<>() ;
//		
//		
//		FXMLLoader fxmlLoader_ =  new FXMLLoader() ;  
//		fxmlLoader_.setLocation(getClass().getResource("Date_FILTER.fxml"));
//		
//		try {
//			dialog.getDialogPane().setContent(fxmlLoader_.load()); 			
//		} 
//		catch (IOException e) {
//			// TODO: handle exception
//		}  
//					
//		
//		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK) ;
//		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL) ; 
//		
//		dialog.setTitle("Form add");
//		DatePicker datePicker = new DatePicker() ; 
//		Date_filter_dialog dialog2 = new Date_filter_dialog(datePicker, arrayList ) ; 
//		fxmlLoader_.setController(dialog2);  
//		dialog2.Date_Process();
//		
//		
//		Optional<ButtonType> resultOptional =  dialog.showAndWait() ;
//
//		if(resultOptional.isPresent() && resultOptional.get() == ButtonType.OK) { 
//			dialog2.getValue();
//			
//				
//		}
//		
//
//	}
	 
}
