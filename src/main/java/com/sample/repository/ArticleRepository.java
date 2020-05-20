package com.sample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	List<Article> findAll();
	Optional<Article> findById(int id);
	List<Article> findByPostTextLike(String word);
	Page<Article> findAll(Pageable pageable);
	Page<Article> findByPostTextLike(Pageable pageable, String word);
	Page<Article> findByIsPublishedIs(Pageable pageable, boolean isPublished);
	Page<Article> findByPostTextLikeAndIsPublishedIs(Pageable pageable, String word, boolean isPublished);
	
}
