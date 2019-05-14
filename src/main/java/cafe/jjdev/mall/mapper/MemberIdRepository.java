package cafe.jjdev.mall.mapper;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Member;

@Mapper
public interface MemberIdRepository {
	public void storetheMemberId(Member member);
	public String selectMemberId(Member member);
}
