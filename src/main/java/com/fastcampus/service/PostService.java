package com.fastcampus.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastcampus.domain.Post;
import com.fastcampus.persistence.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public void insertPost(Post post) {
		postRepository.save(post);
	}

	@Transactional
	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Transactional
	public List<Post> index() {
		return postRepository.findAll();
	}

	@Transactional
	public Post getPost(int id) {
		return postRepository.findById(id).orElse(null);
	}

	@Transactional
	public Page<Post> showPost(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Transactional
	public Post updatePost(Post post) {
		Optional<Post> findEntity = postRepository.findById(post.getId());
		if (findEntity.isPresent()) {
			log.info("is present : " + findEntity.toString());
			Post findPost = findEntity.get();
			findPost.setId(post.getId());
			findPost.setTitle(post.getTitle());
			findPost.setContent(post.getContent());
			Post updated = postRepository.save(findPost);
			return updated;
		} else {
			log.info("null: " + post.toString());
			return null;
		}

	}

	@Transactional
	public String deletePost(int id) {
		log.info("삭제 요청이 들어왔습니다!!");

		// 1: 삭제 대상을 가져온다!
		Post target = postRepository.findById(id).orElse(null);
		log.info(target.toString());

		// 2: 대상을 삭제 한다!
		if (target != null) {
			postRepository.delete(target);
		}
		return "삭제되었습니다.";
	}

}
