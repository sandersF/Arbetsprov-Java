package entity;

public class BookInfo {
		                     
	public Book book;       //Book object that cointains the object Book and
	public int quantity;    //the variable quantity for adding quantity to Cart
	
	public BookInfo(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public boolean bookIsEqualTo(Book book) {
		return this.book.equals(book);
					
	}
}
