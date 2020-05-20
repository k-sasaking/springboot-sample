package com.sample.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sample.form.PostForm;

@Entity
@Table(name = "Article")
public class Article {

    /* Columnの定義 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "post_name", length = 30)
    @NotBlank
	private String postName;
    
    @Column(name = "post_date")
    private Date postDate;
	
    @Column(name = "post_text", length = 200)
    @NotEmpty
	private String postText;
	
    @Column(name = "is_published")
    @NotNull
	private boolean isPublished;
    
	public Article() {

	}
	
	public Article(PostForm form) {
		this.id = form.getId();
		this.postName = form.getPostName();
		this.postText = form.getPostText();
		this.isPublished = form.getIsPublished();
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
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
	public boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	
}
