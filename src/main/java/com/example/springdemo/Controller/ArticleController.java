package com.example.springdemo.Controller;


import com.example.springdemo.DTO.ArticleDTO;
import com.example.springdemo.Service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {

    ArticleService articleService;
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping(path = "/save")
    public ArticleDTO save (@RequestBody ArticleDTO articleDTO){
        return articleService.save(articleDTO);
    }

    //Method to get Article by articleId
    @GetMapping(path = "/{articleId}")
    public ArticleDTO findByAtricleId(@PathVariable int articleId){
        return articleService.findByAtricleId(articleId);
    }

    //Method to get Article by authorId
    @GetMapping
    public List<ArticleDTO> findByAuthorId(@RequestParam int authorId){
        return articleService.findByAuthorId(authorId);
    }

    //Method to get Article by published Date;
    @GetMapping(path = "/publishedon")
    public  List<ArticleDTO> findByPublishedDate(@RequestParam Date  date){
        return articleService.findByPublishedDate(date);
    }

    //Method to get Articles by title;
    @GetMapping(path = "/withtitle")
    public List<ArticleDTO> findByTitle(@RequestParam String title){
        return articleService.findByTitle(title.toLowerCase());
    }

    //Method to get Articles by ispremiumrequired and authorId
    @GetMapping(path = "authorandpremium")
    public List<ArticleDTO> findByAuthorIdAndPremiumRequired(@RequestParam int authorId,
                                                             @RequestParam boolean premiumRequired){
        return articleService.findByAuthorIdAndPremiumRequired(authorId, premiumRequired);
    }


    //MEPPING TO UPDATE ARTICLE CONTENT, TITLE, PREMIUM_REQUIRED AND TO DELETE THE ARTICLE
}
