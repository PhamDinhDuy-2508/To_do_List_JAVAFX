package application;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Save_List extends Controller {
	
	private  static String file_name = "todolist.txt" ; 
	
	public  static  ArrayList <Compoment> list_Compoments =  new ArrayList<>() ; 
	
	private static Save_List save_List =  new Save_List() ;
	
	private DateTimeFormatter dateTimeFormatter ;
	
	
	
	
	public static Save_List get_Save_List() {
		return save_List ; 
	}

	public Save_List() {  		
		super();  
		dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") ; 		
	}  
	public Save_List(DateTimeFormatter dateTimeFormatter) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
	}

	public static void set_Save_List(Save_List _save_List) {
		save_List =  _save_List ;  		
	}

	public  ArrayList<Compoment> getList_Compoments() {
		return list_Compoments;
	}

	public static void setList_Compoments(Compoment _compoment) { 
		
		Compoment compoment =  new Compoment(_compoment.getTitle(), _compoment.getContentString(), _compoment.getDeadlineString()) ; 
		try {
			list_Compoments.add(compoment) ;
		}
		catch (Exception e) {
			System.out.println(e);
		}		
	}  	
	public  void  load_into_listview(String titleString ,  String contenString , String deadlineString) { 
		
		for(Compoment xCompoment : list_Compoments) {
			super.add_TODO(xCompoment.getContentString(), xCompoment.getTitle(), xCompoment.getDeadlineString()); 			
		}
	}
			
	public static ArrayList<Compoment> Load_LIST()throws IOException {
		Path path = Paths.get(file_name) ;  		
		BufferedReader bufferedReader =  Files.newBufferedReader(path) ; 
		String inputString ;   
		
		try { 
			while((inputString =  bufferedReader.readLine())!= null)  {
				 
				String []_inpuString = inputString.split("\t") ; 			
				Compoment compoment = new Compoment(_inpuString[0], _inpuString[1], _inpuString[2]) ;  
				list_Compoments.add(compoment) ; 
				System.out.println(compoment.getContentString());
			}
			
		} catch (Exception e) { 
			
		}	 
		 		
		return list_Compoments ; 
	} 
	
	public void Store_List() throws IOException {
		
			Path path = Paths.get(file_name) ;
			BufferedWriter bufferedWriter = Files.newBufferedWriter(path) ;
		
			try {
				Iterator<Compoment> iter = list_Compoments.iterator() ; 
				while(iter.hasNext()) {
					
					Compoment compoment =  iter.next() ;  
					bufferedWriter.write(String.format("%s\t%s\t%s" , 
							compoment.getTitle() , compoment.getContentString() ,  compoment.getDeadlineString().formatted(dateTimeFormatter)));
					bufferedWriter.newLine();										
				} 		
				bufferedWriter.newLine();										
			}
			finally { 
				if(bufferedWriter != null) {
					bufferedWriter.close();
				}				
			}						 		
	}
	
	public  static boolean Delete_Item_listview(String name_of_Title_String) { 
		for(Compoment xCompoment : list_Compoments) {
			if(xCompoment.getTitle() == name_of_Title_String) {
				list_Compoments.remove(xCompoment) ;  
				return true ;
			}			
		} 
		return false;
	}
	
}
