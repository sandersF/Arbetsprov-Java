package service;

import java.math.BigDecimal;
import java.util.ArrayList;

import entity.Book;
import entity.BookInfo;

public class CartService {
	//Here I store the Books i eventually want to buy
	private ArrayList<BookInfo> books;
	
	public CartService() {
		this.books = new ArrayList<BookInfo>();
	}
	//Get the total price in a BigDecimal variable
	public BigDecimal getTotalPrice() { 
		BigDecimal sum = BigDecimal.ZERO;
		for (BookInfo bookInfo : this.books) {
			sum.add(new BigDecimal(bookInfo.quantity).multiply(bookInfo.book.getPrice())); 
		}
		return sum;
	}
	
	
	//Displays the cart
	public ArrayList<BookInfo> showCart() {
		return this.books;
		
	}
	
	public void addBook(Book book, int quantity ) {		//In addBook() I iterate over an ArrayList<BookInfo> which is the cart
		for(int i = 0; i < this.books.size(); i++) {	//and check if I already have the book in my cart, if so I add the quantity
			BookInfo item = this.books.get(i);          //then I add it to the cart.
			if(item.bookIsEqualTo(book)) {               
				item.quantity += quantity;               
				return;                                  
			} 
		}
		books.add(new BookInfo(book, quantity));
		
	}
	
	public void removeBook(int index, int quantity) {	//Here i choose the index of the books in the store and the quantity that
		BookInfo item = this.books.get(index);          //I want to remove if the quantity is several quantities more then the
		if(quantity >= item.quantity ) {                //quantity of the book in the cart.
			this.books.remove(index);
			return;
		} 
		item.quantity -= quantity;
	}
	
	
	
	
}
