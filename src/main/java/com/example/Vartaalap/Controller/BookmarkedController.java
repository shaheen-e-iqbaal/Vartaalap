package com.example.Vartaalap.Controller;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Service.BookmarkedService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkedController {

    private final BookmarkedService bookmarkedService;

    public BookmarkedController(BookmarkedService bookmarkedService) {
        this.bookmarkedService = bookmarkedService;
    }

    // Add a bookmark
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public String addBookmark(@RequestParam int userId, @RequestParam int articleId) {
        return bookmarkedService.addBookmark(userId, articleId);
    }


    // Or use this more RESTful DELETE version
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public String deleteBookmark(@RequestParam int userId, @RequestParam int articleId) {
        return bookmarkedService.unBookmark(userId, articleId);
    }

    // Get all bookmarks for a user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public List<Article> getBookmarkedArticlesByUser(@PathVariable int userId) {
        return bookmarkedService.findBookmarkedArticlesByUserId(userId);
    }

}
