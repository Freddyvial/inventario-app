package com.poli.inventory.controllers;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.TypeArticle;
import com.poli.inventory.domain.User;
import com.poli.inventory.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getArticles(String idCampus){
        return articleService.getArticle(idCampus);
    }
    @GetMapping("/typeArticle")
    public List<TypeArticle> getTypeArticle(){
        return articleService.getTypeArticle();
    }
    @GetMapping("/articlesByRoom")
    public  List<Article> consulArticleByRoom(Integer idRoom){
        return  articleService.consulArticleByRoom(idRoom);
    }

    @PostMapping(path = "/articles", consumes = "application/json", produces = "application/json")
    public Article setArticles(@RequestBody Article article) throws Exception {
        return articleService.createArticle(article);
    }

    @PostMapping(path = "/changeStateArticle", consumes = "application/json", produces = "application/json")
    public Article changeStateArticle(@RequestBody Article article) throws Exception {
        return articleService.changeStateArticle(article);
    }
    @PostMapping(path = "/changeIdRoomArticle", consumes = "application/json", produces = "application/json")
    public Article changeIdRoomArticle(@RequestBody Article article) throws Exception {
        return articleService.changeIdRoomArticle(article);
    }
    @PostMapping(path = "/listArticlesChange", consumes = "application/json", produces = "application/json")
    public List<Article> changeRoomArticle(@RequestBody List<TypeArticle> typeArticle,String idCampus) throws Exception {
        return articleService.articlesForChanges(typeArticle,idCampus);
    }
}
