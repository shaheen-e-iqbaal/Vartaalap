package com.example.Vartaalap.Controller;


import com.example.Vartaalap.DTO.ArticleDTO;
import com.example.Vartaalap.DTO.LikesDTO;
import com.example.Vartaalap.DTO.TagDTO;
import com.example.Vartaalap.Service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return articleService.findByArticleId(articleId);
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

    @GetMapping(path = "/getall")
    public List<ArticleDTO> findAll(){
        return articleService.findAll();
    }


    //MEPPING TO UPDATE ARTICLE CONTENT, TITLE, PREMIUM_REQUIRED AND TO DELETE THE ARTICLE

    @PutMapping(path = "update")
    public ArticleDTO updateArticle(@RequestParam int articleId,
                                    @RequestBody ArticleDTO articleDTO){
        articleDTO.setArticleId(articleId);
        return articleService.save(articleDTO);
    }

    @DeleteMapping(path = "delete")
    public String deleteArticle(@RequestParam int articleId){
        articleService.deleteByArticleId(articleId);
        return "Deleted Successfully";
    }


    //Method to update Artilces Tags, likes

    @PutMapping(path = "/updatetags")
    public ArticleDTO updateArticleTags(@RequestParam int articleId,
                                        @RequestBody List<TagDTO> tags){
        return articleService.updateArticleTags(articleId,tags);
    }

    @PostMapping(path = "/addlikes")
    public ArticleDTO addArticleLikes(@RequestParam int articleId,
                                      @RequestBody List<LikesDTO> likes){
        return articleService.addArticleLikes(articleId,likes);
    }

    @PutMapping(path = "/removelikes")
    public ArticleDTO removeArticleLikes(@RequestParam int articleId,
                                         @RequestBody List<LikesDTO> likes){
        return articleService.removeArticleLikes(articleId,likes);
    }

    @GetMapping(path = "/withtag/{tag}")
    public List<ArticleDTO> findByTag(@PathVariable String tag){
        return articleService.findByTag(tag);
    }

    @GetMapping(path = "/likedby/{userId}")
    public List<ArticleDTO> findByTag(@PathVariable int userId){
        return articleService.findByUserId(userId);
    }
}
