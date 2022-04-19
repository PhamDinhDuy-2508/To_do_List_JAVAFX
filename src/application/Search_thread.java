package application;

import java.util.ArrayList;

public class Search_thread implements Runnable { 
	 
	private ArrayList<Compoment> compoments =  new ArrayList<Compoment>() ;
	private String titleString ; 
	private Compoment compoment_resCompoment ; 
		
	
	public Search_thread(ArrayList<Compoment> compoments , String tiString) {
		super();
		this.compoments = compoments;
		this.titleString =  tiString ; 
		compoment_resCompoment =  new Compoment() ;		
	}

	public Compoment getCompoment() {
		return compoment_resCompoment;
	}


	public void setCompoments(ArrayList<Compoment> compoments) {
		this.compoments = compoments;
	}
 

	public Compoment Search_Compoment(String List) { 
		for(Compoment xCompoment : compoments) {
			if(xCompoment.getTitle().compareTo(List) == 0 ) { 
				this.compoment_resCompoment =  xCompoment ; 
				compoments.remove(xCompoment) ; 
				return this.compoment_resCompoment ; 
			}
		}
		return null ; 
		
	}
	
	
	public void run() { 		 
		
		Search_Compoment(this.titleString) ; 		
	}

}
