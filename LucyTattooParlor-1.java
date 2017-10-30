import java.util.*;
public class LucyTattooParlor {
		
	public static void main(String[] args){
		//catches command line and sets them to num of artist and how many customers the artists can take per day	
		int numOfArtists = Integer.parseInt(args[0]);
		int customerNum = Integer.parseInt(args[1]);
		//creates the schedule array
		TattooCustomer[][] a = new TattooCustomer[numOfArtists][customerNum];
		
		boolean go = true;
		
		while (go == true){
		
			Scanner input = new Scanner(System.in);
			
			System.out.println("Please enter your name (or if you want to print the schedule, enter Print Waitlist)");
			String customerName = input.nextLine();
			//ends the program is the user wants to print the schedule
			if (customerName.equals("Print Waitlist")){
				break;
			}
			
			System.out.println("What kind of tattoo would you like?");
			String customerTattoo = input.next();
			
			System.out.println("How many minutes do you think your tattoo will take?");
			int customerTime = input.nextInt();
			//creates the object with information taken from user input
			TattooCustomer customer = new TattooCustomer(customerName, customerTattoo, customerTime);		
			
			System.out.println("Enter the index of the artist you wish to request (from 0-" + (numOfArtists - 1) + "). If you just want to get in as fast as possible, enter -1.");
			int request = input.nextInt();	
			
			boolean added = false;
			//if the user wants the shortest waitlist
			if (request == -1){
				added = addCustomer(a, customer);
				
				if (added == true){
					System.out.println("You have been added to the schedule of the soonest available artist!");
				}
				
			}
			//if the user requested a specific artist
			else{
				added = addCustomer(a, customer, request);
				
				if (added == true){
					System.out.println("You have been added to the schedule of the artist at the requested index");
				}
				
			}
		
		}
		
		printSchedule(a);
		
	}
	/** 
	 * This method counts the total time the specific artist has scheduled	
	 * @param a is the one dimensional array of a specific artist's schedule for the day that is passed to this method
	 * @return returns the amount of minutes a specific artist already has scheduled
	**/	
	public static int computeMinutesOfWork(TattooCustomer [] a) {
		
		int totalTimeScheduled = 0;
	
			for(int x = 0; x < a.length; x++){
				//adds up the total time scheduled for an artist
				if(a[x] != null){
					totalTimeScheduled += a[x].getMinutes();
				}
			}
		
		return totalTimeScheduled;
	}
	
	/**
	 * This method is called when the customer requests a specific artist. It finds the next possible scheduling space, as long as there is one open
	 * and as long as the time scheduled computer by computeMinutesOfWork does not exceed 480. The customer is then added to the next scheduling space
	 * for that artist.
	 * @param This method passes in the array of waitlist containing objects of Customer (a), the customer as an object(c), and the artist requested(artistNum).
	 * @return A boolean value representing whether the customer was added to the schedule of the specific artist
	**/
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c, int artistNum) {
		
		boolean trueOrFalse = true;
		
		int nextPossibleSpace = 0;
		//if scheduled time + expected time 
		if((computeMinutesOfWork(a[artistNum]) + c.getMinutes()) > 480){
			trueOrFalse = false;
		}
		//if there are no more spaces in the schedule array
		if(a[artistNum][(a[artistNum].length)-1] != null){
			trueOrFalse = false;
		}
		//if both conditional tests passed
		if(trueOrFalse == true){
		
			for(int y = 0; y < a[artistNum].length; y++){
		
				if (a[artistNum][y] == null){
						nextPossibleSpace = y;
					break;
				}
		
			}
		
			//add artist to the next possible space at index requested
			a[artistNum][nextPossibleSpace] = c;
		
		}
	
		return trueOrFalse;
	
	}
	
	/**
	 * This method is called when the customer requests the shortest wait time. It finds the next possible scheduling space at the index of the shortest wait time,
	 * as long as there is one open. As long as the time scheduled computer by computeMinutesOfWork does not exceed 480. The customer is then added to the next scheduling space
	 * for that artist.
	 * 
	**/
	int numberOfSlotsTaken = 0;
	
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c) { 
		
		boolean trueOrFalse = true;
		
		int nextPossibleSpace = 0;
		int shortestWaitIndex = 0;
		int valueOfShortest = computeMinutesOfWork(a[0]);
		//finds the artist with the most availability and the shortest wait time
		for(int s = 1; s < a.length; s++){
		
			if (computeMinutesOfWork(a[shortestWaitIndex]) > computeMinutesOfWork(a[s])){
		
				shortestWaitIndex = s;
			
			}
		}
		
		int lastIndex = a[shortestWaitIndex].length - 1;
		//if all indexs will exceed 8 hrs when this customer is added
		if((computeMinutesOfWork(a[shortestWaitIndex]) + c.getMinutes()) > 480){
			trueOrFalse = false;
		}
		//if there are no more empty spaces to fill with customers
		if(a[shortestWaitIndex][lastIndex] != null){
			trueOrFalse = false;
		}
		//if we're good to go, without any reason to return false
		if(trueOrFalse == true){
			
			for(int y = 0; y < a[shortestWaitIndex].length; y++){
		
				if (a[shortestWaitIndex][y] == null){
					nextPossibleSpace = y;
					break;
				}
		
			}
		
			//adds customer to artist with shortest wait time
			a[shortestWaitIndex][nextPossibleSpace] = c;
		
		}
	
		return trueOrFalse;
	}
	/**
	 * This method prints the schedule array
	 * @param a is the array of objects that is the schedule array
	**/
	public static void printSchedule(TattooCustomer[][] a){
		
		//prints schedule
		for (int i = 0; i < a.length; i++){
			System.out.print("Artist " + i + ":  ");
			for (int j = 0; j < a[i].length; j++){
				if (a[i][j] != null){
					System.out.print("[" + a[i][j].getName());
					System.out.print("(Tattoo " + a[i][j].getTattoo() + ")" + "(ET: " + a[i][j].getMinutes() + ")" + "] ");
				}
			}
			System.out.println("\n");
		}
		
	}
}
	

		
		

