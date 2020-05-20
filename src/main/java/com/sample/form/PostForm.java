package com.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sample.entity.Article;

public class PostForm {

	private Integer id;

	@NotBlank
	private String postName;
	
	@NotEmpty
	private String postText;
	
	private boolean isPublished;
	
	
	public PostForm() {
		this.isPublished = true;
	}

	public PostForm(Article article) {
		this.id = article.getId();
		this.postName = article.getPostName();
		this.postText = article.getPostText();
		this.isPublished = article.getIsPublished();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
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
