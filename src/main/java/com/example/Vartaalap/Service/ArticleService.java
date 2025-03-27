package com.example.Vartaalap.Service;

import com.example.Vartaalap.DTO.ArticleDTO;
import com.example.Vartaalap.DTO.LikesDTO;
import com.example.Vartaalap.DTO.TagDTO;
import com.example.Vartaalap.Repository.ArticleRepository;
import com.example.Vartaalap.Repository.LikesRepository;
import com.example.Vartaalap.Repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {

    ArticleRepository articleRepository;
    BookmarkedService bookmarkedService;
    LikesRepository likesRepository;
    TagRepository tagRepository;

    public ArticleService(ArticleRepository articleRepository, BookmarkedService bookmarkedService,
                          LikesRepository likesRepository, TagRepository tagRepository){
        this.bookmarkedService = bookmarkedService;
        this.articleRepository = articleRepository;
        this.likesRepository = likesRepository;
        this.tagRepository = tagRepository;
    }

    //Method to save the article
    public ArticleDTO save (ArticleDTO articleDTO){
        for(TagDTO tagDTO : articleDTO.getTags()){
            tagDTO.setArticleDTO(articleDTO);
        }
        return articleRepository.save(articleDTO);
    }

    //Method to get Article by articleId
    public ArticleDTO findByArticleId(int articleId){
        return articleRepository.findByArticleId(articleId);
    }

    //Method to get Article by authorId
    public List<ArticleDTO> findByAuthorId(int authorId){
        return articleRepository.findByAuthorId(authorId);
    }

    //Method to get Article by published Date;
    public  List<ArticleDTO> findByPublishedDate(Date date){
        return articleRepository.findByPublishedDate(date);
    }

    //Method to get Articles by title;
    public List<ArticleDTO> findByTitle(String title){
        return articleRepository.findByTitle(title.toLowerCase());
    }

    //Method to get Articles by premiumrequired and authorId
    public List<ArticleDTO> findByAuthorIdAndPremiumRequired(int authorId, boolean premiumRequired){
        return articleRepository.findByAuthorIdAndPremiumRequired(authorId, premiumRequired);
    }

    //Method to delete article by ArticleId
    public Long deleteByArticleId(int articleId){
        bookmarkedService.deleteByArticleId(articleId);
        return articleRepository.deleteByArticleId(articleId);
    }

    //Method to get All Articles
    public List<ArticleDTO> findAll(){
        return articleRepository.findAll();
    }

    //Method to update the Tags of article
    @Modifying
    @Transactional
    public ArticleDTO updateArticleTags(int articleId, List<TagDTO> tags){
        ArticleDTO articleDTO = articleRepository.findByArticleId(articleId);

        // Clear existing tags to avoid orphaned entities
        articleDTO.getTags().clear();

        // Update the tags collection
        for (TagDTO tagDTO : tags) {
            tagDTO.setArticleDTO(articleDTO); // Set the bidirectional relationship
            articleDTO.getTags().add(tagDTO); // Add the tag to article's tag collection
        }

        // Save the updated article
        return articleRepository.save(articleDTO);
    }


    //Method to add Likes to article
    @Modifying
    @Transactional
    public ArticleDTO addArticleLikes(int articleId, List<LikesDTO> likes){
        ArticleDTO articleDTO = articleRepository.findByArticleId(articleId);
        Set<LikesDTO> st = new HashSet<>(articleDTO.getLikes());
        st.addAll(likes);
        List<LikesDTO> finale = new ArrayList<>(st);
        articleDTO.getLikes().clear();
        for(LikesDTO likesDTO : finale){
            likesDTO.setArticleDTO(articleDTO);
            articleDTO.getLikes().add(likesDTO);
        }
        return articleRepository.save(articleDTO);
    }

    //Method to remove likes from article
    @Modifying
    @Transactional
    public ArticleDTO removeArticleLikes(int articleId, List<LikesDTO> likes){

        ArticleDTO articleDTO = articleRepository.findByArticleId(articleId);
        List<LikesDTO> cur = articleDTO.getLikes();
        for(LikesDTO likesDTO : likes){
            cur.remove(likesDTO);
        }
        articleDTO.getLikes().clear();
        for(LikesDTO likesDTO : cur){
            likesDTO.setArticleDTO(articleDTO);
            articleDTO.getLikes().add(likesDTO);
        }

        return articleRepository.save(articleDTO);

    }

    //Method to get Article with tag
    public List<ArticleDTO> findByTag(String tag){
        List<ArticleDTO> ans = new ArrayList<>();
        List<TagDTO> resp = tagRepository.findByTag(tag);
        for(TagDTO tagDTO : resp){
            ans.add(tagDTO.getArticleDTO());
        }
        return ans;
    }

    //Method to get articled liked by User
    public List<ArticleDTO> findByUserId(int userId){
        List<ArticleDTO> ans = new ArrayList<>();
        List<LikesDTO> resp = likesRepository.findByUserId(userId);
        for(LikesDTO likesDTO: resp){
            ans.add(likesDTO.getArticleDTO());
        }
        return ans;
    }


}
