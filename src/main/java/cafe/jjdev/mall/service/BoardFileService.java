package cafe.jjdev.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.BoardFileMapper;

@Service
@Transactional
public class BoardFileService {
	@Autowired
	private BoardFileMapper boardFileMapper;

	public int removeBoardFile(int boardFileNo) {
		System.out.println("[cafe.jjdev.mall.service.BoardCommentService.removeBoardFile] boardFileNo: "+boardFileNo);
		return boardFileMapper.deleteBoardFileByFileNo(boardFileNo);
	}
	
	
	
}
