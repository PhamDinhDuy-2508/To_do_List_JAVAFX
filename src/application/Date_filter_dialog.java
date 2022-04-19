package application;
 


import java.time.DayOfWeek;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class Date_filter_dialog implements Runnable  {	
		 
	
	private Callback<DatePicker, DateCell> resultCallback ;  
	
	private ArrayList<Compoment> compoments  = new ArrayList<>(); 
	
	private HashMap<LocalDate, ArrayList<Compoment>> list_compoment  =  new HashMap<>(); 
	
	public ArrayList<Compoment> getCompoments() {
		return compoments;
	} 	
	
	public Date_filter_dialog() {
		super();
	}

	public Date_filter_dialog(DatePicker datePicker, ArrayList<Compoment> compoments) {
		super(); 
		this.compoments = compoments; 		
	}

	public Callback<DatePicker, DateCell> getResultCallback() {
		return resultCallback;
	}

	public void setResultCallback(Callback<DatePicker, DateCell> resultCallback) {
		this.resultCallback = resultCallback;
	}

	public void setCompoments(ArrayList<Compoment> compoments) {
		this.compoments = compoments; 				
	}
	
	public void set_List_Compoment(Compoment compoment) { 
		
		LocalDate localDate =  compoment.change_string_date_to_date_date(compoment.getDeadlineString()) ;
		
		if(list_compoment.containsKey(localDate)) {
			this.list_compoment.get(localDate).add(compoment) ; 
		}
		else {
			ArrayList<Compoment> _compoments =  new ArrayList<Compoment>() ; 
			_compoments.add(compoment) ; 
			
			this.list_compoment.put(localDate, _compoments) ;
		}
		
	} 
	
	public  HashMap<LocalDate, ArrayList<Compoment>> get_List_Compoment() {
		 return this.list_compoment ; 		
	}

	public Date_filter_dialog(ArrayList<Compoment> compoments) {
		super();
		this.compoments = compoments;
	}  
	
	public Callback<DatePicker, DateCell> getDayCellFactory() {
		System.out.println("CHECK");
		   Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			  
	            @Override
	            public DateCell call( DatePicker datePicker) {
	                return new DateCell() {
	                
	                    @Override
	                    
	                    public void updateItem(LocalDate item, boolean empty) {
	                        super.updateItem(item, empty);
	                        
	                        for(Compoment xCompoment : compoments) {
	                        	LocalDate localDate = xCompoment.change_string_date_to_date_date(xCompoment.getDeadlineString()) ;   
	                        	if(localDate.compareTo(item) == 0 ) {
	                        		setDisable(false); 
	                        		setStyle("-fx-background-color: #ffc0cb;");	                        		
	                        	}
	                        	
	                        }	                      
	                    }
	                };
	            }
	        }; 
	        return dayCellFactory ; 
	} 	
	
	@Override
	public void run() {
	
		this.resultCallback = getDayCellFactory() ;		
		return ; 
		
	}
					
}
