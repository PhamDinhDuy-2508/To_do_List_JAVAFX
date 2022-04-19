package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database_connection { 
	
	private String titleString  ;  
	private String Content ;   
	private String deadlineString ;  
	private String construct_Day ;  
	
	
	ArrayList<Compoment> infomationArrayList  = new ArrayList<>() ; 
	
	private Connection connection ; 
	
	private Statement statement ; 
	
	
	public Database_connection(String titleString, String contex, String deadlineString, String construct_Day) {
		super();
		this.titleString = titleString;
		this.Content  = contex;
		this.deadlineString = deadlineString;
		this.construct_Day = construct_Day;
		
	}

	public Database_connection() {
		super(); 
		try {
			Connection connection =  DriverManager.getConnection("jdbc:sqlite:/D:\\SQLITE\\database\\test.db") ; 
			statement =  connection.createStatement(); 
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
	} 
	
	public void Add_Infomation(String titile , String Content ,  String deadline ,  String construct_day) {
		try {
			String string = "INSERT INTO Infomation ( day_time , context , title , deadline) VALUES ('" + 
					construct_day + "','" + Content + "','" + titile +"','"+  deadline +"');" ; 
			System.out.println(string);
			
			statement.execute(string)  ;
		} catch (SQLException e) { 
			System.out.println(e.getMessage());
		}				
	}
	public ArrayList<Compoment> Load_Infomation() {  
		
		ArrayList<Compoment> infomation_List =  new ArrayList<>() ; 
		
		try { 
			String qrlString = "SELECT * FROM Infomation " ; 			
						
			ResultSet resultSet = statement.executeQuery(qrlString) ; 
			
			while(resultSet.next()) { 
				System.out.println(resultSet.getString("deadline"));
				Compoment compoment =  new Compoment(resultSet.getString("title") ,  resultSet.getString("context") , resultSet.getString("deadline") ) ; 
				
				infomation_List.add(compoment) ; 
			} 
									
			
		} catch (SQLException e) {
		}
		
		this.infomationArrayList =  infomation_List ; 
		return this.infomationArrayList ; 
	}				

	public void Modify_Item(String titleString , String contentString , String Deadline ) { 
		 
		try { 
			
			String qurl  =" UPDATE Infomation Set title = " + titleString  + " context =  " + contentString + " deadline =  " + Deadline + ";"  ; 
			
			statement.execute(qurl)  ;						
			
		} catch (SQLException e) { 
			System.out.println(e.getMessage());
			
		}
		
	}
	public void Delete_Item(String titleString_nameString ) {
		try { 
			
			String qurlString =  "DELETE FROM Infomation WHERE title = " + titleString_nameString + ";" ; 
			
			statement.execute(qurlString) ; 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
