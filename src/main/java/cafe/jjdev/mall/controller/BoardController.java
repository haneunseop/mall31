package cafe.jjdev.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cafe.jjdev.mall.service.BoardService;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "board/addBoard";
	}
	@PostMapping("/board/addBoard")
	public String addBoard(BoardRequest boardRequest) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.addBoard] POST boardRequest.getBoardFile(): "+boardRequest.getBoardFile());
		//String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString(); //서블릿과  관련된 기능이라 컨트롤러에서 구했다.
		//String path = "D:/haneunseop/jjdev/sts-workspace/mall/src/main/webapp/upload/";
		//반복해서 실행 boardService.addBoard(boardRequest[]);
		
		boardService.addBoard(boardRequest); 
		 
		return "redirect:"+"/board/getBoardList";
	}
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.removeBoard] GET boardNo: "+boardNo);
		model.addAttribute("boardNo", boardNo);
		return "board/removeBoard";
	}
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.removeBoard] POST board: "+board);
		boardService.removeBoard(board);
		return "redirect:"+"/board/getBoardList";
	}
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.modifyBoard] GET boardNo: "+boardNo);
		Map<String, Object> map = boardService.getBoard(boardNo);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		System.out.println("[cafe.jjdev.mall.controller.BoardController.modifyBoard] GET board: "+map.get("board"));
		System.out.println("[cafe.jjdev.mall.controller.BoardController.modifyBoard] GET boardFileList: "+map.get("boardFileList"));
		return "board/modifyBoard";
	}
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(BoardRequest boardRequest) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.modifyBoard] POST board: "+boardRequest);
		boardService.modifyBoard(boardRequest);
		return "redirect:"+"/board/getBoard?boardNo="+boardRequest.getBoardNo();
	}
	@GetMapping("/board/getBoard")
	public String getBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.getBoard] GET boardNo: "+boardNo);
		Map<String, Object> map = boardService.getBoardAndCommentListAndFile(boardNo);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardCommentList", map.get("boardCommentList"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		return "board/getBoard";
	}
	@GetMapping("/board/getBoardList")
	public String getBoardList(Model model, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		System.out.println("[cafe.jjdev.mall.controller.BoardController.getBoardList] GET currentPage: "+currentPage);
		Map<String, Object> returnmap = boardService.getBoardList(currentPage);
		model.addAttribute("list", returnmap.get("list"));
		model.addAttribute("lastPage", returnmap.get("lastPage"));
		model.addAttribute("boardCount", returnmap.get("boardCount"));
		model.addAttribute("currentPage", currentPage);
		return "board/getBoardList";
	}
	
	// 수정 폼에서 파일을 삭제할 때
	@GetMapping("/board/removeBoardFileInModifyBoard")
	public String removeBoardFileInModifyBoard(Model model, BoardFile boardFile){
		System.out.println("[cafe.jjdev.mall.controller.BoardController.removeBoardFileInModifyBoard] GET boardFile: "+boardFile);
		Map<String, Object> map = boardService.removeBoardFileInModifyBoard(boardFile);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		return "board/modifyBoard";
	}
}
