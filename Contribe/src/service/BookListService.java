package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import entity.Book;


public class BookListService implements BookList{

	private ArrayList<Book> allBooks;
	
	public BookListService() {
		
		try {
			
			this.allBooks = getBooks();                     //The constructor loads a ArrayList<Book> from the method getBooks()
		} catch (IOException | ParseException e) {          //if there is any connection errors or parsing errors it creates an
			this.allBooks = new ArrayList<>();              //empty ArrayList<>.
		}	
			
	}
	
	public static BookListService getInstance() {
		return new BookListService();
	}
	

	@Override
	public Book[] list(String searchString) {               //This method returns a list of book objects that matches the passed in parameter searchString
		Book[] list = new Book[this.allBooks.size()];	    //First i check if the searchString variable that is passed in is empty, if so the method
		if(searchString.isEmpty()) {                        //returns all the books that exists in the store. Else I iterate over all the books in the 
			for(int i = 0; i < list.length; i++) {		    //store and check if the searString matches any author, if so the method returns a list of books 		
				list[i] = this.allBooks.get(i);             //that matches that string, else i check if the searchString matches any title, it returns
			}	                                            //a list that matches that string.
			return list;
		} else {
			ArrayList<Book> searchList = new ArrayList<Book>(); 
			for(int i = 0; i < this.allBooks.size(); i++) {
				if(this.allBooks.get(i).getAuthor().contains(searchString)) {
					searchList.add(this.allBooks.get(i));
				}
				else if(this.allBooks.get(i).getTitle().contains(searchString)) {
					searchList.add(this.allBooks.get(i));
				}
			}
			return searchList.toArray(new Book[0]);
		}
	}
	
	@Override
	public boolean add(Book book, int quantity) {           //This method adds books and the quantity of the books to the store
		for(int i = 0; i < this.allBooks.size(); i++) {     //First I iterate over all the books in the store and check each book
			Book item = this.allBooks.get(i);               //to see if the book already exists in the store, if so the method will
			if(item.bookIsEqualTo(book)) {                  //add the quantity added to the store.
				item.quantity += quantity;                  //If the book that is added does not exist it will add the book to the store
				return true;                                //with the quantity.
			} 
		}
		book.quantity = quantity;
		this.allBooks.add(book);
		return true;
	}

	@Override
	public int[] buy(Book... books) {
		int[] results = new int[books.length];
		
		for(int i = 0; i < books.length; i++) {                     //In this method I iterate over all the books that are passed to the method
			for(int j = 0; j < this.allBooks.size() -1; j++) {      //then i iterate over books in the store, if each book that are passed to this 
				if(books[i].equals(this.allBooks.get(j))) {         //method and checks if they exists in the store, if there is more than one
					if(this.allBooks.get(j).quantity >= 1) {        //the method returns a array item with value 0 which mean that the book exists.
						results[i] = 0;                             //else the method returns 1 which mean that is not in stock. 
						continue;                                   //else the book does not exist. Finally we return the books to buy.
					} else {
						results[i] = 1;
						continue;
					}
				}
			}
			results[i] = 2;
			continue;
		}			
		return results;
	}
	
	private ArrayList<Book> getBooks() throws IOException, ParseException {		
		BufferedReader response = null; 			
		URL url = new URL("https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt");
		response = new BufferedReader(
				   new InputStreamReader(                           //In getBooks() i create a BufferedReader with a InputStreamReader and open
				   url.openStream()));                              //a url stream with openStream(). The response from the stream is stored in
			  			                                            //the string input and add the books which are converted in convertToBook() method in a
		String input = null;			                            //in a ArrayList<Book> and return a list of books to the ArrayList<Book> variable allBooks.
		ArrayList<Book> books = new ArrayList<Book>();
	
		while((input = response.readLine()) != null) {	 
			books.add(convertToBook(input));
		}  
		response.close();
		return books;
	}
	
	private Book convertToBook(String bookString) throws ParseException {      //In convertToBook() i convert the string read line by line in getBooks()		
		  String[] props = bookString.split(";");                              //and split the string where ; is read and store each book as a string array
	      DecimalFormat decimalFormat = new DecimalFormat();                   //then I convert the string price in the object Book and return a new Book object.
	      decimalFormat.setParseBigDecimal(true);
	      BigDecimal price = (BigDecimal)decimalFormat.parse(props[2]);	      
	      
	      return new Book(props[0],
	    		  		  props[1],
	    		  		  ((price != null) ? price : BigDecimal.ZERO),
	    		  		  Integer.parseInt(props[3]));	      
	}
	
	
	
	
	
}
