package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardRequest;

@Mapper
public interface BoardMapper {
	// 비밀번호 조회
	public String selectBoardPw(int boardNo);
	// 게시글 전체
	public List<Board> selectBoardList(Map<String, Integer> map);
	// 게시글 전체 개수(페이징)
	public int selectBoardCount();
	// 게시글 상세
	public Board selectBoard(int boardNo);
	// 수정
	public int updateBoard(Board board);
	// 삭제
	public int deleteBoard(Board board);
	// 입력
	public int insertBoard(Board board);
}
