package com.fastcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.domain.Post;
import com.fastcampus.domain.Reply;
import com.fastcampus.dto.PostForm;
import com.fastcampus.persistence.PostRepository;
import com.fastcampus.persistence.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	@Transactional
	public void insertReply(Reply reply) {
		replyRepository.save(reply);
	}

	@Transactional
	public ArrayList<Reply> getReplyList(int postId) {
		ArrayList<Reply> replyList = replyRepository.findByPostId(postId);
		log.info("replyList: {}",replyList);
		return replyList;
		
	}

	@Transactional
	public String deleteByPost(ArrayList<Reply> replyList) {
		for(Reply i : replyList) {
			int replyId = i.getId();
			Reply target = replyRepository.findById(replyId).orElse(null);
			replyRepository.delete(target);
		}
		return "삭제되었습니다.";
	}
	
	@Transactional
	public String deleteReply(int id) {

		// 1: 삭제 대상을 가져온다!
		Reply target = replyRepository.findById(id).orElse(null);

		// 2: 대상을 삭제 한다!
		if (target != null) {
			replyRepository.delete(target);
		}
		return "삭제되었습니다.";
	}
	
	
}
