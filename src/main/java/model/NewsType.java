package model;

public class NewsType {
	private int id;
	private String newsType;
	
	public NewsType() {
	}
	
	public NewsType(int id, String newsType) {
		this.id = id;
		this.newsType = newsType;
	}
	
	public NewsType(String newsType) {
		this.newsType = newsType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
}
