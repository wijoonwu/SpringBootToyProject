package com.fastcampus.persistence;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fastcampus.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	@Override
	ArrayList<Reply> findAll();

	@Query(value = "select * from reply where post_id = :postId", nativeQuery = true)
	ArrayList<Reply> findByPostId(int postId);

}
