package cafe.jjdev.mall.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Member;

@Mapper
public interface MemberMapper {
	public void insertMember(Member member);
	public Member selectMember(Member member);
	public void updateMemberPw(Map<String, String> map);
	public void updateMember(Member member);
	public void deleteMember(Member member);
	public String selectMemberIdByEmail(String memberEmail);
	public String selectMemberPwByIdAndEmail(Member member);
}
