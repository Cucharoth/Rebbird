package com.ufro.Rebbird.service;

import com.ufro.Rebbird.repository.CommentRepository;
import com.ufro.Rebbird.repository.PostRepository;
import com.ufro.Rebbird.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ufro.Rebbird.commons.GenericService;
import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.UserPostReaction;
import com.ufro.Rebbird.model.utils.ReactionType;

@Service
public class PostService extends GenericService<Post, Long> {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPostReactionService userPostReactionService;

    @Autowired
    CommentService commentService;

    public PostService(PostRepository repository) {
        super(repository);
    }

    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Iterable<Post> findAllByOrderByDateDesc() {
        return postRepository.findAllByOrderByDateDesc();
    }

    // public Iterable<Post> findAllByCategoryIdOrderByDateDesc(int categoryId) {
    // return postRepository.findAllByCategoryIdOrderByDateDesc(categoryId);
    // }

    public Page<Post> findAllByCategoryIdOrderByDateDesc(int category, int page) {
        Pageable paging = PageRequest.of(page - 1, 5);
        return postRepository.findAllByCategoryIdOrderByDateDesc(category, paging);
    }

    public int countAllByPostId(Long postId) {
        return commentRepository.countAllByPostId(postId);
    }

    public Iterable<Post> findAllByTitleContainingIgnoreCaseOrderByDateDesc(String keyword) {
        return postRepository.findAllByTitleContainingIgnoreCaseOrderByDateDesc(keyword);
    }

    public Iterable<Post> findAllByAuthorId(int authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }

    /**
     * Da a <i>Post</i> el formato necesario para ser mostrado en <i>Index</i>
     * 
     * @param user         <i>User</i> autentificado
     * @param currentPosts resultado del query de <i>Posts</i> realizado
     * @return <i>List</i> que posee cada uno de los elementos necesarios para ser
     *         mostrados en <i>Index</i>
     * 
     */
    public List<List<Object>> giveFormatPost(User user, Iterable<Post> currentPosts) {
        List<List<Object>> posts = new ArrayList<List<Object>>();
        if (user != null) {
            for (Post post : currentPosts) {
                List<Object> postInfo = new ArrayList<Object>();
                boolean hasReacted = false, isLike = false;
                UserPostReaction userPostReaction = userPostReactionService.findByPostAndUser(post.getId(),
                        user.getId());
                if (userPostReaction != null) {
                    hasReacted = true;
                    isLike = userPostReaction.getReactionType().equals(ReactionType.LIKE);
                }

                // no necesita null handling, JDBC retorna 0 para int vacios.
                int commentAmount = commentService.countAllByPostId(post.getId());

                postInfo.add(post);
                postInfo.add(commentAmount);
                postInfo.add(hasReacted);
                postInfo.add(isLike);
                posts.add(postInfo);
            }
        } else {
            // se hace de esta forma para mantener formato en vista 'index'.
            for (Post post : currentPosts) {
                List<Object> postInfo = new ArrayList<Object>();

                // no necesita null handling, JDBC retorna 0 para int vacios.
                int commentAmount = commentService.countAllByPostId(post.getId());

                postInfo.add(post);
                postInfo.add(commentAmount);
                posts.add(postInfo);
            }
        }
        return posts;
    }
}
