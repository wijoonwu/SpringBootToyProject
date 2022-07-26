package com.fastcampus.persistence;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastcampus.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	@Override
	ArrayList<Post> findAll();
	


}
