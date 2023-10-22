package com.ufro.Rebbird.repository;

import com.ufro.Rebbird.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //Iterable<Post> findAllByCategoryIdOrderByDateDesc(int categoryId);

    Page<Post> findAllByCategoryIdOrderByDateDesc(int categoryId, Pageable pageable);

    Iterable<Post> findAllByAuthorId(int authorId);

    Iterable<Post> findAllByOrderByDateDesc();

    Iterable<Post> findAllByTitleContainingIgnoreCaseOrderByDateDesc(String keyword);

}
