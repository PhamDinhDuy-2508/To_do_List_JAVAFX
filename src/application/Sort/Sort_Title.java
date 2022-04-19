package application.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.Compoment;
import application.Sort.Sort_lenght_of_content.sort_custom;

public class Sort_Title extends Interface_sort {
	private ArrayList<Compoment> list_compomentArrayList  =  new ArrayList<>(); 
	
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
			
	        return o1.getTitle().compareTo(o2.getTitle());
	    } 
		
	}
}
