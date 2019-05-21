package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 글의 no를 매개변수로 받아 해당 글의 댓글 리스트를 리턴한다.
	// 댓글은 한 화면에 5개씩 보여주고, 5개가 넘어가면 페이징을 할 것이다.
	// 댓글 페이지 번호는 5개씩 보여줄 것이고, 이전(다음)을 누르면 5개의 댓글 페이지 번호가 뜰 것이다.
	// 다음이 마지막 페이지일 때는 보이지 않게 할 것이다. 
	public Map<String, Object> getBoardCommentListByBoardNoAndPaging(int boardNo, int currentPage){
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.getBoardCommentListByBoardNoAndPaging] 서비스 실행 확인");
		// 한 페이지에서 댓글이 몇개 보여질 것인지를 정하는 rowPerPage 변수.. LIMIT x, y에서 y
		int rowPerPage = 5;
		// 쿼리 행을 몇번째부터 보여줄 것인지를 정하는 변수.. LIMIT x, y에서 x
		// EX) 현재 1페이지이고, rowPerPage가 5라면 LIMIT는 0,5가 되어야하고
		// 현재 2페이지이고, rowPerPage가 5라면 LIMIT는 5,5가 되어야하고
		// 현재 3페이지이고, rowPerPage가 5라면 LIMIT는 10,5가 되어야한다.
		// 즉 처음은 0이고 그 다음부터는 5의 배수(5, 10, 15, 20, 25~)가 되어야 한다.
		// 댓글 목록 불러오기
		int startRow = (currentPage-1)*rowPerPage;
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.getBoardCommentListByBoardNoAndPaging] startRow: "+startRow);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", boardNo);
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		List<BoardComment> boardCommentList = boardCommentMapper.selectBoardCommentListByBoardNo(map);
		
		// 마지막 페이지 구하기
		// 댓글 전체의 수를 담을 변수를 초기화하고 마지막 페이지의 값을 담을 변수를 선언한다.
		int totalRow = boardCommentMapper.selectBoardCommentCountByBoardNo(boardNo);
		int lastPage;
		// 댓글 전체의 수가 한 페이지에 보여줄 변수의 값으로 나눴을 때, 나머지가 없으면 몫이 마지막 페이지의 값이다.  
		// 댓글 전체의 수가 한 페이지에 보여줄 변수의 값으로 나눴을 때, 나머지가 존재하면 몫에서 1을 더한 값이 마지막 페이지다.
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
		}else {
			lastPage = totalRow / rowPerPage +1;
		}
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.getBoardCommentListByBoardNoAndPaging] lastPage: "+lastPage);
		
		// 리턴하기
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("lastPage", lastPage);
		resultMap.put("boardCommentList", boardCommentList);
		return resultMap;
	}
}
