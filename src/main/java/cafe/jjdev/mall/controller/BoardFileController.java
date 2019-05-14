package cafe.jjdev.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.BoardFileService;


@Controller
public class BoardFileController {
	@Autowired
	private BoardFileService boardFileService;
	
	@GetMapping("/board/removeBoardFile")
	public String removeBoardFile(Model model, @RequestParam(value="boardFileNo", required = true) int boardFileNo) {
		System.out.println("[cafe.jjdev.mall.controller.BoardFileController.removeBoardFile] GET boardFileNo: "+boardFileNo);
		model.addAttribute("boardFileNo", boardFileNo);
		return "board/removeBoardFile";
	}
	@PostMapping("/board/removeBoardFile")
	public String removeBoardFile(@RequestParam(value="boardFileNo", required = true) int boardFileNo) {
		System.out.println("[cafe.jjdev.mall.controller.BoardFileController.removeBoardFile] POST boardFileNo: "+boardFileNo);
		boardFileService.removeBoardFile(boardFileNo);
		return "redirect:"+"board/getBoardList";
	}
}
