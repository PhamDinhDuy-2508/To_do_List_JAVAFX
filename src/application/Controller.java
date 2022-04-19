package application;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import application.Sort.*;
import application.Sort.Connection_Custom.Sort_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class Controller  {    
	 
	@FXML 
	private DatePicker date_Picker ;  
	
	private Thread thread  ; 
	
	private HashMap<String, Compoment> list_compomentHashMap ; 
	@FXML
	private ListView<String> list_view ; 
	
	private  ArrayList<Compoment> present_ArrayList ; 
	
	 
	
	public HashMap<String, Compoment> getList_compomentHashMap() {
		return list_compomentHashMap;
	}
	

	public void setList_compomentHashMap(HashMap<String, Compoment> list_compomentHashMap) {
		this.list_compomentHashMap = list_compomentHashMap;
	}
	
	@FXML 
	private TextArea content ; 
	@FXML 
	private Button delete ;
	@FXML 
	private Label deadline_label ;  
	@FXML	
	private AnchorPane main_page ; 

	@FXML  
		
	private ArrayList<Compoment> todo_ArrayList  ; 
	
	private ContextMenu _ContextMenu ;
	@FXML
	private MenuButton Menu_Button ;  
	Date_filter_dialog date_filter_dialog  ;  
	private  Database_connection database_connection ; 
	
	
			
	
	public void initialize()  throws IOException{  
		 Context_menu_setting();
		 database_connection  =  new Database_connection() ; 
		 System.out.println(database_connection.Load_Infomation().size());

		 
		 ArrayList<Compoment> cheCompoments	 = database_connection.Load_Infomation() ; 		 
		 date_filter_dialog.setCompoments(cheCompoments); 		 
		 this.date_Picker.setShowWeekNumbers(true);
		 
		 this.date_Picker.setDayCellFactory(date_filter_dialog.getDayCellFactory());	

		 
		 		 		 
		 if(!cheCompoments.isEmpty()) { 
			 
			 for(Compoment xCompoment : cheCompoments) { 
				 date_filter_dialog.set_List_Compoment(xCompoment);
				 add_TODO(xCompoment);
			 }  			 
		 }				 
		 Update_ListView_Compoment();

			return  ; 	 
		}
	
	
	public void Context_menu_setting()  {  				
		 _ContextMenu =  new ContextMenu() ;  
				
		MenuItem delete_Item =  new MenuItem("Delete") ;   
		MenuItem Modify_Item  = new MenuItem("Modify item")  ; 
		
		Modify_Item.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent arg0) { 				
				String titleString =  list_view.getSelectionModel().getSelectedItem() ; 
				modify_data(titleString);							
			}		
		}); 
		
				
		delete_Item.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) { 

			String titleString =  list_view.getSelectionModel().getSelectedItem() ;
			Delete_Item(titleString);
		}		
	}); 
		_ContextMenu.getItems().addAll(delete_Item) ; 
		_ContextMenu.getItems().addAll(Modify_Item) ;
	
	}   
	
	public void MenuButton_Item(){}		
	public Controller() {
		super();
		list_view =  new ListView<>() ;  
		list_compomentHashMap = new HashMap<>() ; 
		list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 
		todo_ArrayList =  new ArrayList<Compoment>() ;   
		date_filter_dialog =  new Date_filter_dialog() ; 
				
		
	}   
	 	
	public void add_TODO(String titleString ,String contentString , String deadlineString) {
		Compoment compoment =  new Compoment(titleString, contentString, deadlineString) ;
		if(find_LIst(titleString) == false) { 
			list_compomentHashMap.put(titleString, compoment) ;
			this.list_view.getItems().add(titleString) ;  			
		} 
		
		else { 
			list_compomentHashMap.get(titleString).setContentString(contentString);;
			list_compomentHashMap.get(titleString).setTitle(titleString); 
			list_compomentHashMap.get(titleString).setDeadlineString(deadlineString);

		} 
		todo_ArrayList.add(compoment) ;
		try {
			database_connection.Add_Infomation(titleString, contentString, deadlineString, compoment.getDate_constructString()); 		

		} catch (Exception e) {
			// TODO: handle exception
		}
		Save_List.setList_Compoments(compoment); 
		date_filter_dialog.set_List_Compoment(compoment); 

		return ;  		
	} 
	
	
	
	public void add_TODO(Compoment compoment) {
		String titleString = compoment.getTitle() ; 
		
		
		todo_ArrayList.add(compoment) ; 
		
			if(find_LIst(titleString) == false) { 
			
			list_compomentHashMap.put(titleString, compoment) ;
			this.list_view.getItems().add(titleString) ;  			
		} 
		
		else { 
			list_compomentHashMap.get(titleString).setContentString(compoment.getContentString());;
			list_compomentHashMap.get(titleString).setTitle(compoment.getTitle()); 
			list_compomentHashMap.get(titleString).setDeadlineString(compoment.getDeadlineString());		
			} 
		return ;  			
	}
	
	public boolean find_LIst(String name_of_list) {
		if(!list_compomentHashMap.containsKey(name_of_list) ) {
			return false ; 
		} 
		else {
			return true ; 
		}		
	} 	
		
	@FXML 
	public void handel_listview_mouse_cliked() {
		
		String iString = list_view.getSelectionModel() .getSelectedItem() ;

			Compoment compoment =this.list_compomentHashMap.get(iString) ;
			System.out.println("CONTENT "+ compoment.getDeadlineString());

			content.setText(list_compomentHashMap.get(iString).getContentString()); 
			this.deadline_label.setText(compoment.getDeadlineString());	
	} 
	
	public void Update_ListView_Compoment() { 
		
		list_view.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
		
			@Override
			public ListCell<String> call(ListView<String> arg0) { 
				ListCell<String>  cell =  new ListCell<String>() {
				
					@Override 
					protected void updateItem(String itemCompoment , boolean Empty) {
						super.updateItem(itemCompoment, Empty); 
						if(Empty) {
							setText(null);
						} 
						else { 						
							setText(itemCompoment);
							if(list_compomentHashMap.get(itemCompoment).compair_date())  {
								setTextFill(Color.RED);
							}							
						}						
					}										
				} ;  
				cell.emptyProperty().addListener((ob,wasEmp ,IsNowEmp)->{
						if(IsNowEmp) {
							cell.setContextMenu(null) ; 
						} 
						else {
							cell.setContextMenu(_ContextMenu) ; 
						}
						}
						);
				return  cell ; 
			} 
		});			
	} 
		
	public void Delete_Item(String Title) {		
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;  
		alert.setTitle("Confirm") ;  
		alert.setHeaderText("Do you want to delete this Item"); 
		alert.setContentText("Are uou sure ? Press Ok to confirm or cancel to Back "); ;   
		
		Optional<ButtonType> resultOptional =  alert.showAndWait() ;  
		if(resultOptional.isPresent() && resultOptional.get() == ButtonType.OK) {  
			
			list_view.getItems().removeAll(Title) ; 
			
			database_connection.Delete_Item(Title);
			
			
		}

	}  
	public void modify_data(String title)  { 
		
		Dialog<ButtonType> dialog  =  new Dialog<>() ;
		
		//dialog.initOwner(main_page.getScene().getWindow());
		
		FXMLLoader fxmlLoader =  new FXMLLoader() ;  
		fxmlLoader.setLocation(getClass().getResource("Dialog.fxml"));
		
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load()); 			
		} 
		catch (IOException e) {
			// TODO: handle exception
		}  
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK) ;
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL) ; 
		
		dialog.setTitle("Form add");
		Dialog_class dialog_class = fxmlLoader.getController() ;
		
		dialog_class.Set_all_infor(list_compomentHashMap.get(title));
		
		Optional<ButtonType> resultOptional =  dialog.showAndWait() ;
		
		Search_thread search_thread  =  new Search_thread(todo_ArrayList, title) ;  
		
		if(resultOptional.isPresent() && resultOptional.get() == ButtonType.OK) {
											
			
			dialog_class.Get_data_from_form();   
			content.setText(dialog_class.get_content_area()); 
			

						
			if(title.compareTo(dialog_class.get_title()) == 0) {				
					
					list_compomentHashMap.get(title).setDeadlineString(dialog_class.get_deadline());
					
					content.setText(dialog_class.get_content_area()); 
					deadline_label.setText(dialog_class.get_deadline());
					
			} 															 
			else { 
				list_view.getItems().removeAll(title) ;  
				 
				
				
				add_TODO(dialog_class.get_title(), dialog_class.get_content_area(), dialog_class.get_deadline());
			} 
			try {
				
				 Compoment compoment = search_thread.getCompoment() ; 
				 compoment.setContentString(dialog.getContentText());
				 compoment.setTitle(dialog_class.get_title()); 
				 compoment.setDeadlineString(dialog_class.get_deadline()); 
				 database_connection.Modify_Item(dialog_class.get_title(), dialog.getContentText(), dialog_class.get_deadline());
				 
			} catch (Exception e) { 
				System.out.println(e);
			}
			list_compomentHashMap.get(title).setContentString(dialog_class.get_content_area()); 

			
		}		 							
	} 
		
	@FXML 
	public void Add_clicked(MouseEvent mouseEvent) { 
		 
		System.out.println("add clicked ");
		
	}  
	@FXML
	public void Add_button_clicked() {
	Dialog<ButtonType> dialog  =  new Dialog<>() ;
		
		dialog.initOwner(main_page.getScene().getWindow());
		FXMLLoader fxmlLoader =  new FXMLLoader() ;  
		fxmlLoader.setLocation(getClass().getResource("Dialog.fxml"));
		
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load()); 			
		} 
		catch (IOException e) {
			// TODO: handle exception
		}  
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK) ;
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL) ; 
		
		dialog.setTitle("Form add");
		
		Optional<ButtonType> resultOptional =  dialog.showAndWait() ; 
		if(resultOptional.isPresent() && resultOptional.get() == ButtonType.OK) {
			
			Dialog_class dialog_class_ = fxmlLoader.getController() ; 
			dialog_class_.Get_data_from_form();
			System.out.println("Oke");
			add_TODO(dialog_class_.get_title(), dialog_class_.get_content_area(), dialog_class_.get_deadline());
		}		 
	}  
					 
	@FXML 
	public void Deadline_Sort(ActionEvent event) {		
		Interface_sort deadline_Interface_sort = Connection_Custom.Connection_model(Sort_model.Deadline) ;    
		
		deadline_Interface_sort.Set_Array_List_Compoment(todo_ArrayList);
		
		load_List_view(deadline_Interface_sort.sort_result() ,"Deadline") ; 			
		
	}  
	@FXML
	public void Title_Sort(ActionEvent  event) { 
		
		Interface_sort Title_Interface_sort = Connection_Custom.Connection_model(Sort_model.Title) ;    
		
		Title_Interface_sort.Set_Array_List_Compoment(todo_ArrayList);
		
		load_List_view(Title_Interface_sort.sort_result() ,"Title") ; 				
		
	}  
	@FXML
	public void Content_Sort(ActionEvent event) {
		
		Interface_sort Content_Interface_sort = Connection_Custom.Connection_model(Sort_model.Content) ;    
		
		Content_Interface_sort.Set_Array_List_Compoment(todo_ArrayList);
		
		load_List_view(Content_Interface_sort.sort_result() ,"Content") ; 		
	}
	
	public void load_List_view(ArrayList<Compoment> arrayList,String titleString) { 
		
		Menu_Button.setText(titleString);
		
		this.list_view.getItems().clear();
		
		
		for(Compoment xCompoment : arrayList) {
			list_view.getItems().add(xCompoment.getTitle()) ; 
		}
	}
	
	@FXML 
	public void Filter_Search() {
		
		HashMap<LocalDate, ArrayList<Compoment>>  checkHashMap = date_filter_dialog.get_List_Compoment() ; 
		
		list_view.getItems().clear();  
		 
		
		for(Compoment xCompoment : checkHashMap.get(date_Picker.getValue())) { 
			System.out.println(xCompoment.getTitle());
			list_view.getItems().add(xCompoment.getTitle()) ; 		
		}		
	}
		
	@FXML 
	public void Filter_Action() { 							
		Filter_Search();

				
	} 
	@FXML
	public void Reload() throws IOException { 
		
		list_view.getItems().clear(); 
				
	for(String xCompoment : this.list_compomentHashMap.keySet() ) { 			
			list_view.getItems().add(xCompoment) ; 			
		}
	 this.date_Picker.setDayCellFactory(date_filter_dialog.getDayCellFactory());		
	}	
						 						
} 


