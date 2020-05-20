package com.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sample.entity.Article;
import com.sample.form.PostForm;

public interface PostService {
	
	public List<Article> getAllArticles();
	public Optional<Article> getArticleById(int id);
	public void createArticle(PostForm form);
	public void updateArticle(Article article);
	public void deleteArticle(Integer id);
	public List<Article> searchArticles(String word);
	public Page<Article> getAllArticles(Pageable pageable);
	public Page<Article> searchArticles(Pageable pageable, String word);
	public Page<Article> getAllArticles(Pageable pageable, boolean isPublished);
	public Page<Article> searchArticles(Pageable pageable, String word, boolean isPublished);

}
