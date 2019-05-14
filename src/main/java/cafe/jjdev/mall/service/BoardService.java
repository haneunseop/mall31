package cafe.jjdev.mall.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.commons.ConstantPath;
import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.mapper.BoardMapper;
import cafe.jjdev.mall.mapper.BoardFileMapper;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	@Autowired
	private BoardFileMapper boardFileMapper;
	
	public Map<String, Object> getBoardList(int currentPage) {
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoardList] currentPage: "+currentPage);
		// 요청 가공
		final int ROW_PER_PAGE = 10; //상수
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rowPerPage", ROW_PER_PAGE);
		map.put("beginRow", beginRow);
		
		List<Board> list = boardMapper.selectBoardList(map);
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = boardCount/ROW_PER_PAGE;
		if(boardCount % ROW_PER_PAGE != 0) {
			lastPage++;
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("boardCount", boardCount);
		// 모델값(리턴값) 가공
		return returnMap;
	}
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}
	public Map<String, Object> getBoard(int boardNo) {
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoard] boardNo: "+boardNo);
		// 게시글 상세 조회
		Board board = boardMapper.selectBoard(boardNo);
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoard] board: "+board);
		// 첨부파일 delete가 1인 것을 0으로 바꾼다.
		boardFileMapper.updateBoardFile(boardNo);
		// 첨부파일 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFile(boardNo);
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoard] boardFileList: "+boardFileList);
		//리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardFileList", boardFileList);
		return map;
	}
	public Map<String, Object> getBoardAndCommentListAndFile(int boardNo) {
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoardAndCommentListAndFile] boardNo: "+boardNo);
		// 상세 조회
		Board board = boardMapper.selectBoard(boardNo);
		// 댓글 목록 조회
		List<BoardComment> boardCommentList = boardCommentMapper.selectBoardCommentListByBoardNo(boardNo);
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoardAndCommentListAndFile] boardCommentList: "+boardCommentList);
		// 첨부파일 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFile(boardNo);
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoardAndCommentListAndFile] boardFileList: "+boardFileList);
		System.out.println("---------------------------------------------------------");
		System.out.println("[cafe.jjdev.mall.service.BoardService.getBoardAndCommentListAndFile] boardFileList.size: "+boardFileList.size());
		System.out.println("---------------------------------------------------------");
		//리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardCommentList", boardCommentList);
		map.put("boardFileList", boardFileList);
		return map;
	}
	
	public void modifyBoard(BoardRequest boardRequest) {
		System.out.println("[cafe.jjdev.mall.service.BoardService.modifyBoard] boardRequest: "+boardRequest);
		Board board = new Board();
		board.setBoardNo(boardRequest.getBoardNo());
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardUser(boardRequest.getBoardUser());
		System.out.println("[cafe.jjdev.mall.service.BoardService.modifyBoard] board: "+board);
		// 게시글 수정
		boardMapper.updateBoard(board);
		// 실제 파일 삭제 및 db의 레코드 삭제
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileForDelete(boardRequest.getBoardNo());
		File deleteFile = null;
		if(boardFileList.size() != 0) {
			System.out.println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 파일 삭제 실행----------------");
			for(BoardFile boardFile : boardFileList ) {
				deleteFile = new File(ConstantPath.UPLOAD_PATH+boardFile.getBoardFileSaveName()+"."+boardFile.getBoardFileExt());
				deleteFile.delete();
				boardFileMapper.deleteBoardFileByFileNo(boardFile.getBoardFileNo());
			}
		}

		// 파일 생성
		boolean root = false;
			for(MultipartFile multipartFile : boardRequest.getBoardFile()) {
				System.out.println("[cafe.jjdev.mall.service.BoardService.modifyBoard] multipartFile.getSize(): "+multipartFile.getSize());
				if(multipartFile.getSize() > 0 ) {
					root = true;
				}
				if(root) {
					String originalFileName = multipartFile.getOriginalFilename(); 
					int i = originalFileName.lastIndexOf(".");
					String originName = originalFileName.substring(0, i);
					String ext = originalFileName.substring(i+1).toLowerCase(); //확장자를 소문자로 바꾼다.
					UUID uuid = UUID.randomUUID(); 
					String saveName = uuid.toString().replace("-", "");
					BoardFile boardFile = new BoardFile();
					boardFile.setBoardNo(board.getBoardNo());
					boardFile.setBoardFileOriginName(originName);
					boardFile.setBoardFileSaveName(saveName);
					boardFile.setBoardFileExt(ext);
					boardFile.setBoardFileSize(multipartFile.getSize());
					boardFile.setBoardFileType(multipartFile.getContentType());
					System.out.println("[cafe.jjdev.mall.service.BoardService.modifyBoard] boardFile: "+boardFile);
					File file = new File(ConstantPath.UPLOAD_PATH+saveName+"."+ext);
					try {
						multipartFile.transferTo(file); 
					} catch (Exception e) { 
						e.printStackTrace();
						throw new RuntimeException(); 
					} 
					// 파일 정보를 db에 입력
					boardFileMapper.insertBoardFile(boardFile); 
				}
			}
		}
		
	/*
	 * if(boardRequest.getBoardFile() != null) { System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0----------------------------------------"
	 * ); for(MultipartFile multipartFile : boardRequest.getBoardFile()) { String
	 * originalFileName = multipartFile.getOriginalFilename(); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0-1----------------------------------------originalFileName: "
	 * +originalFileName); int i = originalFileName.lastIndexOf("."); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0-2---------------------------------------i: "
	 * +i); String originName = originalFileName.substring(0, i); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0-3---------------------------------------originName: "
	 * +originName); String ext = originalFileName.substring(i+1).toLowerCase();
	 * //확장자를 소문자로 바꾼다. System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0-4---------------------------------------"
	 * ); UUID uuid = UUID.randomUUID(); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인0-5---------------------------------------"
	 * ); String saveName = uuid.toString().replace("-", ""); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인1----------------------------------------"
	 * ); BoardFile boardFile = new BoardFile();
	 * boardFile.setBoardNo(board.getBoardNo()); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인2----------------------------------------boardFile.getBoardNo(): "
	 * +boardFile.getBoardNo()); boardFile.setBoardFileOriginName(originName);
	 * System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인3----------------------------------------boardFile.getBoardFileOriginName(): "
	 * +boardFile.getBoardFileOriginName());
	 * boardFile.setBoardFileSaveName(saveName); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인3----------------------------------------boardFile.getBoardFileSaveName(): "
	 * +boardFile.getBoardFileSaveName()); boardFile.setBoardFileExt(ext);
	 * System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인5----------------------------------------boardFile.getBoardFileExt(): "
	 * +boardFile.getBoardFileExt());
	 * boardFile.setBoardFileSize(multipartFile.getSize()); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인6----------------------------------------boardFile.getBoardFileSize(): "
	 * +boardFile.getBoardFileSize());
	 * boardFile.setBoardFileType(multipartFile.getContentType()); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.modifyBoard] 실행 확인7----------------------------------------boardFile..getBoardFileType(): "
	 * +boardFile.getBoardFileType()); System.out.
	 * println("[cafe.jjdev.mall.service.BoardService.addBoard] boardFile: "
	 * +boardFile); File file = new File(ConstantPath.UPLOAD_PATH+saveName+"."+ext);
	 * try { multipartFile.transferTo(file); } catch (Exception e) {
	 * e.printStackTrace(); throw new RuntimeException(); } // 파일 정보를 db에 입력
	 * boardFileMapper.insertBoardFile(boardFile); } } }
	 */
	public int removeBoard(Board board) {
		// 비밀번호 조회
		String result = boardMapper.selectBoardPw(board.getBoardNo());
		System.out.println("[cafe.jjdev.mall.service.BoardService.removeBoard] result: "+result);
		if(result.equals(board.getBoardPw())) {
			//db의 코멘트 테이블 삭제
			boardCommentMapper.deleteBoardCommentByBoardNo(board.getBoardNo());
			//실제 파일 삭제
			List<BoardFile> boardFileList = boardFileMapper.selectBoardFile(board.getBoardNo());
			System.out.println("[cafe.jjdev.mall.service.BoardService.removeBoard] boardFile: "+boardFileList);
			File file = null;
			if(boardFileList.size() != 0) {
				System.out.println("[cafe.jjdev.mall.service.BoardService.removeBoard] 실행확인----------------");
				for(BoardFile boardFile : boardFileList ) {
					file = new File(ConstantPath.UPLOAD_PATH+boardFile.getBoardFileSaveName()+"."+boardFile.getBoardFileExt());
					file.delete();
				}
			}
			//db의 파일 테이블 삭제
			boardFileMapper.deleteBoardFileByBoardNo(board.getBoardNo());
			
			//db의 보드 테이블 삭제
			int deleteBoardResult = boardMapper.deleteBoard(board);
			System.out.println("[cafe.jjdev.mall.service.BoardService.removeBoard] deleteBoardResult: "+deleteBoardResult);
		}
		return 0;
	}
	
	public void addBoard(BoardRequest boardRequest) {
		System.out.println("[cafe.jjdev.mall.service.BoardService.addBoard] boardRequest: "+boardRequest);
		//게시글 입력
		Board board = new Board();
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardUser(boardRequest.getBoardUser());
		System.out.println("[cafe.jjdev.mall.service.BoardService.addBoard] board: "+board);
		boardMapper.insertBoard(board);
		//파일생성
		boolean root = false;
		for(MultipartFile multipartFile : boardRequest.getBoardFile()) {
			System.out.println("[cafe.jjdev.mall.service.BoardService.addBoard] multipartFile.getSize(): "+multipartFile.getSize());
			if(multipartFile.getSize() > 0 ) {
				root = true;
			}
			if(root) {
				String originalFileName = multipartFile.getOriginalFilename(); 
				int i = originalFileName.lastIndexOf(".");
				String originName = originalFileName.substring(0, i);
				String ext = originalFileName.substring(i+1).toLowerCase(); //확장자를 소문자로 바꾼다.
				UUID uuid = UUID.randomUUID(); 
				String saveName = uuid.toString().replace("-", "");
				BoardFile boardFile = new BoardFile();
				boardFile.setBoardNo(board.getBoardNo());
				boardFile.setBoardFileOriginName(originName);
				boardFile.setBoardFileSaveName(saveName);
				boardFile.setBoardFileExt(ext);
				boardFile.setBoardFileSize(multipartFile.getSize());
				boardFile.setBoardFileType(multipartFile.getContentType());
				System.out.println("[cafe.jjdev.mall.service.BoardService.addBoard] boardFile: "+boardFile);
				File file = new File(ConstantPath.UPLOAD_PATH+saveName+"."+ext);
				try {
					multipartFile.transferTo(file); 
				} catch (Exception e) { 
					e.printStackTrace();
					throw new RuntimeException(); 
				} 
				// 파일 정보를 db에 입력
				boardFileMapper.insertBoardFile(boardFile); 
			}
		}
		/*
		 * if(boardRequest.getBoardFile() != null) { for(MultipartFile multipartFile :
		 * boardRequest.getBoardFile()) { String originalFileName =
		 * multipartFile.getOriginalFilename(); int i =
		 * originalFileName.lastIndexOf("."); String originName =
		 * originalFileName.substring(0, i); String ext =
		 * originalFileName.substring(i+1).toLowerCase();//확장자를 소문자로 바꾼다. UUID uuid =
		 * UUID.randomUUID(); String saveName = uuid.toString().replace("-", "");
		 * BoardFile boardFile = new BoardFile();
		 * boardFile.setBoardNo(board.getBoardNo());
		 * boardFile.setBoardFileOriginName(originName);
		 * boardFile.setBoardFileSaveName(saveName); boardFile.setBoardFileExt(ext);
		 * boardFile.setBoardFileSize(multipartFile.getSize());
		 * boardFile.setBoardFileType(multipartFile.getContentType()); System.out.
		 * println("[cafe.jjdev.mall.service.BoardService.addBoard] boardFile: "
		 * +boardFile); File file = new File(ConstantPath.UPLOAD_PATH+saveName+"."+ext);
		 * try { multipartFile.transferTo(file); } catch (Exception e) {
		 * e.printStackTrace(); throw new RuntimeException(); }
		 * boardFileMapper.insertBoardFile(boardFile); } }
		 */
	}
	
	public Map<String, Object> removeBoardFileInModifyBoard(BoardFile boardFile){
		// board_file_delete를 1로 바꿔서 삭제 대기 상태로 만든다.
		boardFileMapper.updateBoardFileForDelete(boardFile.getBoardFileNo());
		/*BoardFile boardFile = boardFileMapper.selectBoardFileOne(ParameterboardFile.getBoardFileNo());
		 * // 실제 파일 삭제 File file = new
		 * File(ConstantPath.UPLOAD_PATH+boardFile.getBoardFileSaveName()+"."+boardFile.
		 * getBoardFileExt()); file.delete(); //db에 저장된 파일 데이터 삭제
		 * boardFileMapper.deleteBoardFileByFileNo(boardFile.getBoardFileNo());
		 */
		// 게시글 상세 조회
		Board board = boardMapper.selectBoard(boardFile.getBoardNo());
		// 첨부파일 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFile(boardFile.getBoardNo());
		//리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardFileList", boardFileList);
		return map;
	}
}


