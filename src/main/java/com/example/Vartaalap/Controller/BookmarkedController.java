package com.example.Vartaalap.Controller;


import com.example.Vartaalap.Service.BookmarkedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bookmark")
public class BookmarkedController {

    BookmarkedService bookmarkedService;

    public BookmarkedController(BookmarkedService bookmarkedService){
        this.bookmarkedService = bookmarkedService;
    }

    @GetMapping(path = "/bookmarkedarticlesby/{userId}")
    public String getBookmarkedArticlesbyUserId(@PathVariable int userId){
        List<Integer> res = bookmarkedService.findBookmarkedArticlesByUserId(userId);
        if(res.size() == 0)return "No bookmarked article for this user";
        return res.toString();
    }

    @PostMapping(path = "/dobookmark")
    public String BookmarkArticle (@RequestParam String userId,
                                   @RequestParam String articleId){
        return bookmarkedService.BookmarkArticle(Integer.parseInt(userId), Integer.parseInt(articleId));
    }

    @PostMapping(path = "/unbookmark")
    public String unBookmark(@RequestParam int userId,
                             @RequestParam int articleId){
        return bookmarkedService.unBookmark(userId, articleId);
    }
}
