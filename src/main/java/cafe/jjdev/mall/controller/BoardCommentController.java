package cafe.jjdev.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.BoardCommentService;
import cafe.jjdev.mall.vo.BoardComment;

@Controller
public class BoardCommentController {
	@Autowired
	private BoardCommentService boardCommentService;
	
	@PostMapping("/board/addBoardComment")
	public String addBoardComment(BoardComment boardComment) {
		System.out.println("[cafe.jjdev.mall.controller.BoardCommentController.addBoardComment] POST boardComment: "+boardComment);
		boardCommentService.addBoardComment(boardComment);
		return "redirect:"+"getBoard?boardNo="+boardComment.getBoardNo();
	}
	
	@GetMapping("/board/removeBoardComment")
	public String removeBoardComment(Model model, BoardComment boardComment) {
		System.out.println("[cafe.jjdev.mall.controller.BoardCommentController.removeBoardComment] GET boardComment: "+boardComment);
		model.addAttribute("boardComment", boardComment);
		return "board/removeBoardComment";
	}
	
	@PostMapping("/board/removeBoardComment")
	public String removeBoardComment(BoardComment boardComment) {
		System.out.println("[cafe.jjdev.mall.controller.BoardCommentController.removeBoardComment] POST boardComment: "+boardComment);
		boardCommentService.removeBoardComment(boardComment);
		return "redirect:"+"/board/getBoard?boardNo="+boardComment.getBoardNo();
	}
}
