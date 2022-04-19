package application;


import java.time.LocalDate;




import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Dialog_class {
	
	 
	private String _title ;  
	 
	private String _content_area ;  
	 
	private String _deadline ;
	@FXML 
	private TextField title ;  
	@FXML 
	private TextArea content_area ;  
	@FXML 
	private DatePicker deadline ;
	 
	public Dialog_class() { 		
		super() ; 
	}    
		
	public void Set_all_infor(Compoment compoment) {
		this.title.setText(compoment.getTitle()); 
		this.content_area.setText(compoment.getContentString()); 
		this.deadline.setValue(compoment.change_string_date_to_date_date(compoment.getDeadlineString())); 
		set_content_area(content_area.getText()); 
		set_title(_title); 
		set_deadline();
		
		return ; 
	}
	
	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_content_area() {
		return _content_area;
	}
	
	public void set_content_area(String _content_area) {
		this._content_area = _content_area;
	}

	public String get_deadline() {
		return _deadline;
	}
	
	public void set_deadline() {
	
		LocalDate localDate =  deadline.getValue() ;
		this._deadline = localDate.toString();

	}

	public void Get_data_from_form() {		
		set_content_area(content_area.getText());
		set_deadline(); 
		set_title(title.getText()) ; 
		System.out.println(this._deadline);
	}

	public  boolean compair_date() { 
		LocalDate localDate_nowDate = LocalDate.now() ; 		
		LocalDate localDate_deadlineDate =  deadline.getValue() ; 
		
		if(localDate_nowDate.isAfter(localDate_deadlineDate)) { 
			return true ; 			
		} 
		else if(localDate_deadlineDate.isBefore(localDate_deadlineDate) || localDate_deadlineDate == localDate_nowDate) {
			
			return false ; 
		}
		return false  ; 		
	}
		
	

}
