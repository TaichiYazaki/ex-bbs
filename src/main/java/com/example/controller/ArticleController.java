package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("login")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpForm2() {
		return new CommentForm();
	}

	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findAll();
		for (Article aaa : articleList) {
			List<Comment> commentList = commentService.findByArticleId(aaa.getId());
			aaa.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		return "show";
	}

	@RequestMapping("insert")
	public String insertArticle(Article article, ArticleForm form, Model model) {
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		List<Article> allList = articleService.findAll();
		model.addAttribute("allList", allList);
		return "redirect:/login";
	}

	@RequestMapping("insert2")
	public String insertComment(Comment comment, CommentForm form, Model model) {
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(form.getArticleId());
		commentService.insertCommnet(comment);
		model.addAttribute("comment", comment);
		return "redirect:/login";
	}

	@RequestMapping("delete")
	public String deleteArticle(Integer articleId) {
		commentService.deleteComment(articleId);
		articleService.delete(articleId);
		return "redirect:/login";
	}
}
