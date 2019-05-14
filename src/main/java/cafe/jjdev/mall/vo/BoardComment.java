package cafe.jjdev.mall.vo;

public class BoardComment {
	private int boardCommentNo;
	private int boardNo;
	private String boardCommentPw;
	private String boardCommentContent;
	private String boardCommentUser;
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBoardCommentNo() {
		return boardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardCommentPw() {
		return boardCommentPw;
	}
	public void setBoardCommentPw(String boardCommentPw) {
		this.boardCommentPw = boardCommentPw;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public String getBoardCommentUser() {
		return boardCommentUser;
	}
	public void setBoardCommentUser(String boardCommentUser) {
		this.boardCommentUser = boardCommentUser;
	}
	@Override
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardNo=" + boardNo + ", boardCommentPw="
				+ boardCommentPw + ", boardCommentContent=" + boardCommentContent + ", boardCommentUser="
				+ boardCommentUser + "]";
	}
	
}
