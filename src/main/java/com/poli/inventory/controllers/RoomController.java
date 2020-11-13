package com.poli.inventory.controllers;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.User;
import com.poli.inventory.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getArticles(){
        return articleService.getArticle();
    }

    @PostMapping(path = "/articles", consumes = "application/json", produces = "application/json")
    public Article setArticles(@RequestBody Article article) throws Exception {

        return articleService.createArticle(article);

    }
}
