import java.util.*;
public class TattooCustomer {
	
	private String name;
	private String tattoo;
	private int minutes;
	
	public TattooCustomer (String name, String tattoo, int minutes){
		
		this.name = name;
		this.tattoo = tattoo;
		this.minutes = minutes;
		
	}
	//methods in order to access private variables for the object
	public String getName(){
		return this.name;
	}
	
	public String getTattoo(){
		return this.tattoo;
	}
	
	public int getMinutes(){
		return this.minutes;
	}	
}
