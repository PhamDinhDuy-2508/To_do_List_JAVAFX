package application.Sort;

import java.time.LocalDate
;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import application.Compoment;

public class Sort_dead_line  extends Interface_sort{ 
		
	private ArrayList<Compoment> list_compomentArrayList  =  new ArrayList<>(); 
	
	public void Set_Array_List_Compoment( ArrayList<Compoment>  args) { 
		
		this.list_compomentArrayList = args ; 
		
		return ; 
	}
	
	public  ArrayList<Compoment> sort_result() { 	
		 
		System.out.println(this.list_compomentArrayList.size());
		
		Collections.sort(list_compomentArrayList , new Comparator<Compoment>() {
			
			public int compare(Compoment o1 ,  Compoment o2) {
				
				LocalDate localDateo1 = o1.change_string_date_to_date_date(o1.getDeadlineString()) ; 
				LocalDate localDateo2 = o2.change_string_date_to_date_date(o2.getDeadlineString()) ; 
				
				
				return localDateo1.compareTo(localDateo2) ; 
			}
						
		});  		
			
						
	 	return this.list_compomentArrayList ; 
	} 	 
			
}


 