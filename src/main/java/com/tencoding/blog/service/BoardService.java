package com.tencoding.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
// 꼭 서비스만 써야함
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public void write(Board board, User user) {

		// 가독성을 위하여 한번 더 지정
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);

	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		
		Page<Board> page =  boardRepository.findAll(pageable);
		System.out.println(page.getSize());
		
		return boardRepository.findAll(pageable);
	}
}
