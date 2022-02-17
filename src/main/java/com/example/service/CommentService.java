package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> findByArticleId(Integer articleId) {
		return commentRepository.findByArticleId(articleId);
	}

	public void insertCommnet(Comment comment) {
		commentRepository.insertComment(comment);
	}

	public void deleteComment(Integer articleId) {
		commentRepository.deleteByArticled(articleId);
	}
}
