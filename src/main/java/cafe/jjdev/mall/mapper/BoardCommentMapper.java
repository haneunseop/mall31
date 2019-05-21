package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import cafe.jjdev.mall.vo.BoardComment;

@Mapper
public interface BoardCommentMapper {
	public int insertBoardComment(BoardComment boardComment);
	
	public List<BoardComment> selectBoardCommentListByBoardNo1(int boardNo);
	
	// startRow와 rowPerPage를 받아 LIMIT를 채우고
	// 글의 no를 매개변수로 받아 no에 해당하는 댓글 목록을 리턴한다.
	public List<BoardComment> selectBoardCommentListByBoardNo(Map<String, Object> map);
	// 글의 no를 매개변수로 받아 no에 해당하는 댓글 목록의 수를 리턴한다.
	public int selectBoardCommentCountByBoardNo(int boardNo);
	
	public int deleteBoardCommentByBoardNo(int boardNo);
	public int deleteBoardComment(BoardComment boardComment);
}
