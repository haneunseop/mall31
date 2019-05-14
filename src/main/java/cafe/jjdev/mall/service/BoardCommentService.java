package cafe.jjdev.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.vo.BoardComment;

@Service
@Transactional
public class BoardCommentService {
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	
	public int addBoardComment(BoardComment boardComment) {
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.addBoardComment] boardComment: "+boardComment);
		return boardCommentMapper.insertBoardComment(boardComment);
	}
	
	public int removeBoardComment(BoardComment boardComment) {
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.removeBoardComment] boardComment: "+boardComment);
		return boardCommentMapper.deleteBoardComment(boardComment);
	}
}
