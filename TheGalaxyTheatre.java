
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DecimalFormat;



public class TheGalaxyTheatre {

	static Scanner sc = new Scanner(System.in);
	//Movies' names
	static String [] movies = {"The Batman (Rating: 15A) \n","Death On The Nile (Rating: 12A)\n","Scream (Rating: 18)\n","Spider Man: No Way Home (Rating: 12A)\n"};
	//Show timings
	static String[] typeOfMovie = {"Matinee movie\n", "Evening movie\n"};
	
	//movie number, show time, number of adults, number of under 18s
	static int number, movieType, numAdult, numUnder18;
	//count for chances to enter discount code
	static int chance = 0;
	//discount amount
	static double discountAmount = 0;
	//price without discount
	static double price;
	//whether user wants discount or not 
	static char discount;
	//discount code, credit card number
	static String discountCode, creditCard;
	
	//function displays welcome line and calls get movie function
	public static void welcome() {
		System.out.println ("\nWelcome to The Galaxy Theathre!");
		System.out.println("1. Buy Ticket");
		System.out.println("2. Exit");
		switch(sc.nextInt()) {
			case 1: getMovie();break;
			case 2: System.exit(0);break;
			default:System.out.println("\nPlease choose correct option!");welcome();break;
		}
	}
	
	//this function gets the movie input and checks whether it is valid or not
	//if the input is valid it calls getMovieType function
	public static void getMovie() {
		System.out.println("\nPlease select the number of the movie: ");
		for(int i =0 ; i<movies.length;i++) {
			System.out.println(i+1+". "+movies[i]);
		}
		number = sc.nextInt();
		if(number > 0 && number <= movies.length) {
			System.out.println("\nYou selected the movie " + movies[number-1]);
			getMovieType();
		}
		else {
			System.out.println("\nYou selected Invalid movie. Please select again!");
			getMovie();
		}
	}

	//this function gets the movie show time input and checks whether it is valid or not
	//if the input is valid it calls getTicketType function
	public static void getMovieType() {
		System.out.println("\nPlease select the show time: ");
		for(int i =0 ; i<typeOfMovie.length;i++) {
			System.out.println(i+1+". "+typeOfMovie[i]);
		}
		movieType = sc.nextInt();
		if(movieType > 0 && movieType <= typeOfMovie.length) {
			System.out.println("\nYou selected the " + typeOfMovie[movieType-1]);
			getTicketType();
		}
		else {
			System.out.println("\nYou selected Invalid show time. Please select again!");
			getMovieType();
		}
	}

	//this function gets the ticket type input and checks whether it is valid or not
	//if the input is valid it calls getDiscount function
	public static void getTicketType() {
		System.out.print("\nHow many Adult tickets do you want? ");
		numAdult = sc.nextInt();
		if(numAdult > 0 || number!=3) {
			System.out.print("\nHow many Under 18 tickets do you want? ");
			numUnder18 = sc.nextInt();
			getDiscount();
		}
		else {
			System.out.println("\nThe Scream has a calssification of adults. There must be at least one adult in the party.");
			getMovie();
		}
		
	}

	//this function gets the discount code input and checks whether it is valid or not
	//if the input is valid it calls getPrice function
	public static void getDiscount() {
		Scanner cc = new Scanner(System.in);
		System.out.print("\nDo you have a discount code? [Y/N]: ");
		discount = sc.next().charAt(0);
		if(discount == 'Y' || discount == 'y') {
			System.out.print("\nEnter the discount code: ");
			discountCode = cc.nextLine();
			if(discountCode.equalsIgnoreCase("GTDISC") || discountCode.equalsIgnoreCase("GTTEN")) {
				System.out.println("\n You entered: " + discountCode);
				discountAmount = 4;
			}
			else {
				chance++;
				if(chance <= 3) {
					System.out.println("\n You entered Invalid discount code. Please try again!");
					getDiscount();
				}
			}
		}
		getPrice();
	}

	//this function calculates the price and displays it
	//it then calls the creditCard function
	public static void getPrice() {
		double matineePriceAdult = 6.50;
		double matineePriceUnder18 = 4.00;
	
		double eveningPriceAdult = 9.90;
		double eveningPriceUnder18 = 5.50;
		
		if(movieType == 1) {
			price = numAdult*matineePriceAdult + numUnder18*matineePriceUnder18;
		}
		else if(movieType == 2) {
			price = numAdult*eveningPriceAdult + numUnder18*eveningPriceUnder18;
		}
		
		System.out.println("\n The tickets price for the movie " + movies[number-1] + " is €" + price);
		if(discountAmount>0) {
			System.out.println("\n The tickets price after discount is €" + (price-discountAmount));
		}
		System.out.println("\n You can pay for the tickets using credit card.");
		creditCard();

	}

	//this function gets the credit card input and checks whether it is valid or not
	//if the input is valid it hides the first 12 digits and calls printTicket function
	public static void creditCard() {
		Scanner cc = new Scanner(System.in);
		System.out.print("\nEnter the credit card number: ");
		creditCard = cc.nextLine();
		if(!creditCard.matches("[0-9]{16}")) {

			System.out.println ("\n You entered Invalid Credit Card number!");
			creditCard();
		}
		else {
			String last = creditCard.substring(12, 16);
			creditCard = "XXXX-XXXX-XXXX-"+last;
			System.out.println ("\n You entered: " + creditCard + ".");
			printTicket();
		}
	}

	//this function prints the ticket
	static void printTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy"); 
		Date today = new Date (); 
		System.out.println("\n");
		System.out.printf( "%60s",dateFormat.format(today));
		System.out.println("\n");
		System.out.printf("%40s","THE GALAXY THEATRE");
		System.out.println("\n");
		System.out.print("Tickets for: ");
		System.out.println(typeOfMovie[movieType-1]);
		System.out.printf("\nUnder 18: "+numUnder18 , "%10s");
		System.out.printf("\nAdult: "+numAdult,"%10s");
		System.out.println("\n");
		System.out.printf("\nTicket Cost: "+price,"%10s");
		if(discountAmount>0) {
			System.out.printf("\nDiscount: "+discountAmount,"%10s");
			System.out.printf("\nTotal Cost: "+(price-discountAmount),"%10s");
		}
		System.out.println("\n");
		System.out.println("\nPaid by credit card "+ creditCard);
		getMovie();
}	
}

	
