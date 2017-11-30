package de.burandt.artists.blog.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "blogpost")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private Date postDate;
	
	@Column
	private String headline;
	
	@Column
	private Clob postText;
	
	@Transient
	private String postTextAsString;

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Clob getPostText() {
		return postText;
	}

	public void setPostText(Clob postText) {
		this.postText = postText;
	}

	public Integer getId() {
		return id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getPostTextAsString() throws IOException, SQLException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(postText.getCharacterStream());
		
		String string;
		while ((string = reader.readLine()) != null) {
			sb.append(string);
		}
		return sb.toString();
	}
	
	
	
	
}
