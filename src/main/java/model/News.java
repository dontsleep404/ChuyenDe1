package model;

import java.sql.Date;

public class News {
	private int id;
	private String newsTitle;
	private String newsContent;
	private int newsType;
	private int author;
	private Date createDate;
	private String image;
	
	private String authorName;
	private String newTypeName;
	
	public News() {
	}

	public News(int id, String newsTitle, String newsContent, int newsType, int author, Date createDate, String image) {
		super();
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsType = newsType;
		this.author = author;
		this.createDate = createDate;
		this.image = image;
	}
	
	public News(String newsTitle, String newsContent, int newsType, int author, Date createDate, String image) {
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsType = newsType;
		this.author = author;
		this.createDate = createDate;
		this.image = image;
	}

	
	public News(int id, String newsTitle, String newsContent, int newsType, int author, Date createDate, String image,
			String authorName, String newTypeName) {
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsType = newsType;
		this.author = author;
		this.createDate = createDate;
		this.image = image;
		this.authorName = authorName;
		this.newTypeName = newTypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public int getNewsType() {
		return newsType;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getNewTypeName() {
		return newTypeName;
	}

	public void setNewTypeName(String newTypeName) {
		this.newTypeName = newTypeName;
	}
	
	
}
