package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.entity.Article;
import com.sample.service.PostService;


@Controller
public class SearchController {
	
	@Autowired
    private PostService postService;
	
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(
    		Model model,
    		@PageableDefault(page = 0, size = 5) 
    		Pageable pageable,
    		@RequestParam(name = "word", required = false)
    		String searchWord) {
    	
    	Page<Article> articles = null;
    	if("".equals(searchWord)) {
        	articles = postService.getAllArticles(pageable, true);
    	}
    	else{
    		articles = postService.searchArticles(pageable, searchWord, true);
    	}
    	
    	model.addAttribute("articles", articles); 
    	model.addAttribute("word", searchWord); 
    	
        return "index";
    }
        
}
