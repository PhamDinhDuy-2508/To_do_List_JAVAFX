package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Compoment {
	private String Title ;  
	private String contentString ;  
	private String deadlineString ;  
	private String date_constructString  ; 
	private LocalDate deadline_local_Date ; 
	
	public Compoment() {
		super() ; 
	}
	public Compoment(String title, String contentString, String deadlineString) {
		super();
		Title = title;
		this.contentString = contentString;
		this.deadlineString = deadlineString;
		LocalDate loccaLocalDate =  LocalDate.now() ;  
		this.date_constructString =  loccaLocalDate.toString() ; 
	}

	public String getContentString() {
		
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public String getDeadlineString() {
		return deadlineString;
	}

	public void setDeadlineString(String deadlineString) {
		this.deadlineString = deadlineString;
	}

	public String getDate_constructString() {
		return date_constructString;
	}

	public void setDate_constructString(String date_constructString) {
		this.date_constructString = date_constructString;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	public LocalDate change_string_date_to_date_date(String localdateString) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ; 
		LocalDate dealine_Date =  LocalDate.parse(localdateString, formatter) ;
		
		return dealine_Date	 ; 
		
	}
	public LocalDate getDeadline_local_Date() {
		return deadline_local_Date;
	}

	public void setDeadline_local_Date(LocalDate deadline_local_Date) {
		this.deadline_local_Date = deadline_local_Date;
	} 
	public  boolean compair_date() { 
		LocalDate localDate_nowDate = change_string_date_to_date_date(this.date_constructString) ; 		
		LocalDate localDate_deadlineDate = change_string_date_to_date_date(deadlineString) ; 
		
		if(localDate_nowDate.isAfter(localDate_deadlineDate)) { 
			return true ; 			
		} 
		else if(localDate_deadlineDate.isBefore(localDate_deadlineDate) || localDate_deadlineDate == localDate_nowDate) {
			
			return false ; 
		}
		return false  ; 		
	}				
 	
}
