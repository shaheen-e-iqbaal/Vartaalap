package com.example.Vartaalap.Service;

import com.example.Vartaalap.Repository.BookmarkedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkedService {


    BookmarkedRepository bookmarkedRepository;

    public BookmarkedService(BookmarkedRepository bookmarkedRepository){
        this.bookmarkedRepository = bookmarkedRepository;
    }

    public List<Integer> findBookmarkedArticlesByUserId(int userId){
        return bookmarkedRepository.AllBookmarkedArticlesByUserId(userId);
    }

    public boolean isAlreadyBookmarked(int userId, int articleId){
        List<Integer> res = bookmarkedRepository.isAlreadyBookmarked(userId, articleId);
        return res.size() > 0;
    }

    public String BookmarkArticle (int userId, int articleId){
        boolean check = isAlreadyBookmarked(userId, articleId);
        if(check)return "This article is already Bookmarked by user";
        bookmarkedRepository.BookmarkArticle(userId, articleId);
        return "Article Bookmarked Succesfully";
    }

    public String unBookmark(int userId, int articleId){
        if(isAlreadyBookmarked(userId, articleId)){
            bookmarkedRepository.deleteByUserIdAndArticleId(userId, articleId);
            return "Removed from Bookmarked Successfully";
        }
        else{
            return "No such Bookmarked Article for this User";
        }

    }

    public void deleteByArticleId(int articleId){
        bookmarkedRepository.deleteByArticleId(articleId);
    }
}
