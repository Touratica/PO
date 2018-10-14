package com.mycompany.app;

public class Document {
	private String date;
	private String title;

	public Document(String date) {
		this.date = date;
	}
	public Document(String date, String title) {
		this.date = date;
		this.title = title;
	}

	public String getDate() {
		return this.date;
	}
	public String getTitle() {
		return this.title;
	}

	public void summarize() {
		if (this.title != null) {
			System.out.println(this.title + ", written on " + this.date + ", was summarized.");
		}
		else {
			System.out.println("The document written on " + this.date + " was summarized.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Document) {
			Document doc = (Document) obj;
			return this.date.equals(doc.getDate()) && this.title.equals(doc.getTitle());
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.title != null) {
			return "Document(" + this.date + ", " + this.title;
		}
		return "Document(" + this.date + ")";
	}
}