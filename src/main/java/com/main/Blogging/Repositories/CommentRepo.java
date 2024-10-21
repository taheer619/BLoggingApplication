package com.main.Blogging.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.Blogging.model.Comment;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
