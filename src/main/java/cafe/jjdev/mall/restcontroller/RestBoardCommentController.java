package cafe.jjdev.mall.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.jjdev.mall.service.BoardCommentService;

@RestController
public class RestBoardCommentController {
	@Autowired private BoardCommentService boardCommentService;
	
	@PostMapping(value="/getBoardComment")
	public Map<String,Object> getBoardComment(@RequestParam(value = "boardNo") int boardNo, @RequestParam(value="currentPage", defaultValue = "1") int currentPage){
		// 매개변수가 잘 들어왔는지 확인
		System.out.println("[cafe.jjdev.mall.restcontroller.RestBoardCommentController.getBoardComment(POST)] boardNo: "+boardNo);
		System.out.println("[cafe.jjdev.mall.restcontroller.RestBoardCommentController.getBoardComment(POST)] currentPage: "+currentPage);
		// 페이징에 사용할 lastPage와 댓글 목록 그리고 현재 페이지를 map에 담아서 리턴
		// 현재 페이지를 보내는 이유는 뷰에서 currentPage가 없기 때문
		Map<String,Object> map = boardCommentService.getBoardCommentListByBoardNoAndPaging(boardNo, currentPage);
		map.put("currentPage", currentPage);
		return map;
	}
}
