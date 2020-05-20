package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.entity.Article;
import com.sample.service.PostService;

@Controller
public class IndexController {
	
	@Autowired
    private PostService postService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(
    		Model model,
    		@PageableDefault(page = 0, size = 5) 
    		Pageable pageable
    	) {
    	
    	Page<Article> articles = postService.getAllArticles(pageable, true);
    	
    	model.addAttribute("articles", articles); 
    	
        return "index";
    }
        
}