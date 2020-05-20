package com.sample.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.entity.Article;
import com.sample.service.PostService;

@Controller
public class AdminController {
	
	@Autowired
    private PostService postService;
	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(
    		Model model,
    		@RequestParam("loginSuccess") 
    		Optional<String> loginSuccess,
    		@PageableDefault(page = 0, size = 5) 
    		Pageable pageable
    	) {
    	
    	Page<Article> articles = postService.getAllArticles(pageable);
    	
    	if(loginSuccess.isPresent()) {
    		model.addAttribute("message", "login successed");
    		model.addAttribute("messageType", "success");
    	}
    	model.addAttribute("isAdmin", true); 
    	model.addAttribute("articles", articles); 
    	
        return "index";
    }	
	
    @RequestMapping(value = "/admin/search", method = RequestMethod.GET)
    public String search(
    		Model model,
    		@PageableDefault(page = 0, size = 5) 
    		Pageable pageable,
    		@RequestParam(name = "word", required = false)
    		String searchWord) {
    	
    	Page<Article> articles = null;
    	if("".equals(searchWord)) {
        	articles = postService.getAllArticles(pageable);
    	}
    	else{
    		articles = postService.searchArticles(pageable, searchWord);
    	}
    	
    	model.addAttribute("isAdmin", true); 
    	model.addAttribute("articles", articles); 
    	model.addAttribute("word", searchWord); 
    	
        return "index";
    }
    
    
    @RequestMapping(value = "/admin/publish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String[] doPublish(@PathVariable Integer id) {

        String[] result = { "error" };

        Optional<Article> article = postService.getArticleById(id);
        if (article.isPresent()) {
        	article.get().setIsPublished(true);
        	postService.updateArticle(article.get());
            result[0] = "success";
        }
        
        return result;
    }
    
    @RequestMapping(value = "/admin/unpublish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String[] doUnpublish(@PathVariable Integer id) {

        String[] result = { "error" };

        Optional<Article> article = postService.getArticleById(id);
        if (article.isPresent()) {
        	article.get().setIsPublished(false);
        	postService.updateArticle(article.get());
            result[0] = "success";
        }
        
        return result;
    }

        
}