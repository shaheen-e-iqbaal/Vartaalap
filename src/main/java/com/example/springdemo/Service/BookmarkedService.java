package com.example.springdemo.Service;

import com.example.springdemo.DTO.BookmarkedDTO;
import com.example.springdemo.Repository.BookmarkedRepository;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(res.size() > 0)return true;
        return false;
    }

    public String BookmarkArticle (int userId, int articleId){
        boolean check = isAlreadyBookmarked(userId, articleId);
        if(check == true)return "This article is already Bookmarked by user";
        bookmarkedRepository.BookmarkArticle(userId, articleId);
        return "Article Bookmarked Succesfully";
    }

    public String unBookmark(int userId, int articleId){
        if(isAlreadyBookmarked(userId, articleId) == true){
            bookmarkedRepository.deleteByUserIdAndArticleId(userId, articleId);
            return "Removed from Bookmarked Successfully";
        }
        else{
            return "No such Bookmarked Article for this User";
        }

    }
}
