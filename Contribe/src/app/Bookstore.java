//This work assignment was created by Federico Sanders for Contribe
//2018-04-13
package app;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Book;
import entity.BookInfo;
import service.BookList;
import service.BookListService;
import service.CartService;


public class Bookstore {
	

	public static void main(String[] args) throws IOException, ParseException, InterruptedException{
		
		boolean isRunning = true;
		int number = 0;
		boolean isNumber;
		Scanner input = new Scanner(System.in);                      //I did not waste any time building a proper user interface due to the time.
		BookList bookService = new BookListService();                //The main() method was only used for test purpose, but gives a hands on how 
		CartService cart = new CartService();                        //the application eventually would run.
		
		System.out.print("\t\t*** BOOK STORE ***\n\n");
		System.out.print("  Options:\n\n");
		System.out.print("\tSee Inventory:\tPress (1)\n");
		System.out.print("\tSearch Book:  \tPress (2)\n");
		System.out.print("\tAdd To Cart:  \tPress (3)\n\n");
		System.out.print("  Input: ");
		
		while(isRunning) {
			do {
				
				if(input.hasNextInt()) {
					number = input.nextInt();
					isNumber = true;
					break;
				} else {
					System.out.println("\nInvalid Input: Please Try Again...\n");
					Thread.sleep(2000);
					isNumber = false;
					System.out.print("  Input: ");
					input.next();
				}
			} while (!(isNumber) || (number < 1) || (number > 3));
			
			switch(number) {
			case 1: Book[] books =  bookService.list("");
					System.out.println("\n");
					for(int i = 0; i < books.length; i++) {
					System.out.println(books[i].showBook());
					}
					break;
					
			case 2: Book[] searchList =  bookService.list("Swede");
					System.out.println("\n");
					for(int i = 0; i < searchList.length; i++) {
					System.out.println(searchList[i].showBook());
					}
					break;

			case 3: Book[] bookList =  bookService.list("");
					System.out.println("\n");
					for(int i = 0; i < bookList.length; i++) {
					System.out.println(bookList[i].showBook());
					}
					//readLine choose
					Book bookChoise = bookList[3];
					//readLine quantity
					cart.addBook(bookChoise, 3);
					//readLine ready to buy
					ArrayList<BookInfo> cartBooks = cart.showCart();
					ArrayList<Book> booksToBuy = new ArrayList<Book>();
					for(BookInfo bookInfo : cartBooks) {
						for(int i = 0; i < bookInfo.quantity; i++) {
							booksToBuy.add(bookInfo.book);
						}	
					}
					bookService.buy(booksToBuy.toArray(new Book[0]));
					break;
					
			default: break;
			}	
			input.close();
		}
	}

}










