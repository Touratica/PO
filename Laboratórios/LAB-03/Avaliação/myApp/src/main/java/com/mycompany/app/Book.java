package com.mycompany.app;

public class Book extends Document{
	private String author;

	public Book(String date, String title, String author) {
		super(date, title);
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
	}

	public void review() {
		System.out.println(this.getTitle() + " has been reviewed");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book book = (Book) obj;
			return super.equals(obj) && this.author.equals(book.getAuthor());
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "is a book written by " + this.author;
	}
}