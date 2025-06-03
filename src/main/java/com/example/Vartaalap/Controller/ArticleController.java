package com.example.Vartaalap.Controller;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Comment;
import com.example.Vartaalap.Models.Tag;
import com.example.Vartaalap.Service.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/article", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // ---------- Create / Update ----------
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Article saveArticle(@RequestBody Article article) {
        return articleService.save(article);
    }

    @PutMapping("/update")
    public Article updateArticle(@RequestParam int articleId, @RequestBody Article article) {
        article.setArticleId(articleId);
        return articleService.save(article);
    }

    @PutMapping("/updatetags")
    public Optional<Article> updateTags(@RequestParam int articleId, @RequestBody Set<Tag> tags) {
        return articleService.updateArticleTags(articleId, tags);
    }

    // ---------- Read ----------
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable int articleId) {
        Optional<Article> article = articleService.findByArticleId(articleId);
        if(!article.isPresent()){
            return ResponseEntity.status(404).body("No Article present with id = " + articleId);
        }
        return ResponseEntity.status(200).body(article.get());
    }

    @GetMapping("/author")
    public List<Article> getArticlesByAuthor(@RequestParam int authorId) {
        return articleService.findByAuthorId(authorId);
    }

    @GetMapping("/published")
    public List<Article> getArticlesByPublishedDate(@RequestParam LocalDateTime date) {
        return articleService.findByPublishedDate(date);
    }

    @GetMapping("/title")
    public List<Article> getArticlesByTitle(@RequestParam String title) {
        return articleService.findByTitle(title);
    }

    @GetMapping("/author/premium")
    public List<Article> getByAuthorAndPremium(@RequestParam int authorId,
                                               @RequestParam boolean premiumRequired) {
        return articleService.findByAuthorIdAndPremiumRequired(authorId, premiumRequired);
    }

    @GetMapping("/tag/{tag}")
    public List<Article> getArticlesByTag(@PathVariable String tag) {
        return articleService.findByTag(tag);
    }

    @GetMapping("/all")
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @GetMapping("/comments/{articleId}")
    public List<Comment> getCommentsForArticle(@PathVariable long articleId) {
        return articleService.getComments(articleId);
    }

    // ---------- Delete ----------
    @DeleteMapping("/delete")
    public String deleteArticle(@RequestParam int articleId) {
        articleService.deleteByArticleId(articleId);
        return "Deleted Successfully";
    }
}
