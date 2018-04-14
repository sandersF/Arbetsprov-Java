package entity;

import java.math.BigDecimal;

public class Book {                              ////Book object that contain Book
	private String title;
	private String author;
	private BigDecimal price = BigDecimal.ZERO;
	public int quantity;
	
	public Book() {}
	
	
	
	public Book(String title, String author, BigDecimal price, int quantity) {
				this.title = title;
				this.author = author;
				this.price = price;
				this.quantity += quantity;
	}
	
	public void setTitle(String title) {
			this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String showBook() {
		return " Title: " + this.title  + ", Author: " + this.author + ", Price: " + this.price.toString() + ", Quantity: " + String.valueOf(this.quantity);
	
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public boolean bookIsEqualTo(Book book) {
		return this.equals(book);
					
	}

	public String getAuthor() {
		return this.author;
	}
	
	public String getTitle() {
		return this.title;
	}


	
}