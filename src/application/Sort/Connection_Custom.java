package application.Sort;




public class Connection_Custom { 
	public enum Sort_model{ 	
		Deadline , 
		Title , 
		Content	
	}
	public Connection_Custom() { 
		super() ; 
	} 
	public static Interface_sort Connection_model(Sort_model sort) { 
		Interface_sort interface_sort =  null ;   
		
		switch (sort) {
		case Deadline : {			 
			
			interface_sort  =  new Sort_dead_line() ;    
			
			return interface_sort ; 				
		} 
		case Title : {
			 
			interface_sort =  new Sort_Title()  ;   
			
			return interface_sort ; 						
		}  
		case Content : {
			 
			interface_sort =  new Sort_lenght_of_content() ;   
			
			return interface_sort  ; 
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " ); 			
		}		
	}
}
