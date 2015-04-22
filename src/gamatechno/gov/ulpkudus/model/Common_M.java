package gamatechno.gov.ulpkudus.model;

public class Common_M {
	private String title, date, author, content;

	public Common_M() {
	}

	public Common_M(String title, String date, String author, String content) {
		this.title = title;
		this.date = date;
		this.author = author;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
