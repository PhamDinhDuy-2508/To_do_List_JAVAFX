package application.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.Compoment;

public class Sort_lenght_of_content extends Interface_sort { 
	private ArrayList<Compoment> list_compomentArrayList  =  new ArrayList<>(); 
	private ArrayList<String> resultArrayList = new ArrayList<String>() ;  
	
	public void Set_Array_List_Compoment( ArrayList<Compoment>  args) {		 	
		this.list_compomentArrayList = args ;	
	}
	
	public  ArrayList<Compoment> sort_result() { 	
		
		Collections.sort(this.list_compomentArrayList ,  new sort_custom());  
		
		
	 	return this.list_compomentArrayList ; 
	} 	 
	
	public class sort_custom implements Comparator<Compoment> { 		
		@Override
	    public int compare(Compoment o1, Compoment o2) { 
			
	        return o1.getContentString().compareTo(o2.getContentString());
	    } 
		
	}

}
