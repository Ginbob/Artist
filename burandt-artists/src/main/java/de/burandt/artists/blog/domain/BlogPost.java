package de.burandt.artists.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "blogpost")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date postDate;
	
	@Column
	private String headline;
	
	@Column
	private String postText;
	
	@Column
	private boolean markedAsDeleted;
	
	public BlogPost() {}
	
	public BlogPost(Date postDate, String headline, String postText) {
		super();
		this.postDate = postDate;
		this.headline = headline;
		this.postText = postText;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public boolean isMarkedAsDeleted() {
		return markedAsDeleted;
	}

	public void setMarkedAsDeleted(boolean markedAsDeleted) {
		this.markedAsDeleted = markedAsDeleted;
	}
}
