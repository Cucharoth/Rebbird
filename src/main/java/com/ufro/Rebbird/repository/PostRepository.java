package com.ufro.Rebbird.repository;

import com.ufro.Rebbird.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Iterable<Post> findAllByCategoryIdOrderByDateDesc(int categoryId);

    Iterable<Post> findAllByAuthorId(int authorId);

    Iterable<Post> findAllByOrderByDateDesc();

    // Post findById();

}
