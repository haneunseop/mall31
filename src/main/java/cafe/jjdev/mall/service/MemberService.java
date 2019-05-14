package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.commons.EmailServicelmpl;
import cafe.jjdev.mall.mapper.MemberIdRepository;
import cafe.jjdev.mall.mapper.MemberMapper;
import cafe.jjdev.mall.vo.EmailInfo;
import cafe.jjdev.mall.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	@Autowired private MemberIdRepository memberIdRepository;
	@Autowired private EmailServicelmpl emailServicelmpl;
	// 회원 가입
	public void addMember(Member member) {
		String id = memberIdRepository.selectMemberId(member);
		System.out.println("[cafe.jjdev.mall.service.MemberService.addMember] id: "+id);
		if(id == null) {
			memberMapper.insertMember(member);
			memberIdRepository.storetheMemberId(member);
		}else {
			System.out.println("[cafe.jjdev.mall.service.MemberService.addMember] 이미 있는 아이디이다.");
		}
	}
	
	// 로그인
	public int login(HttpSession session, Member member) {
		int result = 0; // 로그인 성공하면 숫자를 바꿀 것
		Member loginMember = memberMapper.selectMember(member);
		if(loginMember == null) {
			System.out.println("[cafe.jjdev.mall.service.MemberService.login] 존재하지 않는 회원");
		}else {
			session.setAttribute("memberId", loginMember.getMemberId());
			session.setAttribute("memberPw", loginMember.getMemberPw());
			session.setAttribute("memberLevel", loginMember.getMemberLevel());
			System.out.println("[cafe.jjdev.mall.service.MemberService.login] memberId: "+session.getAttribute("memberId"));
			System.out.println("[cafe.jjdev.mall.service.MemberService.login] memberPw: "+session.getAttribute("memberPw"));
			System.out.println("[cafe.jjdev.mall.service.MemberService.login] memberLevel: "+session.getAttribute("memberLevel"));
			result = 1;
		}
		return result;
	}
	
	// 로그아웃
	public void logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
	}
	
	// 멤버 조회
	public Member getMember(Member member) {
		return memberMapper.selectMember(member);
	}
	
	// 세션에 담긴 id와 pw로 멤버 조회
	public Member getMemberBySession(HttpSession session) {
		Member member = new Member();
		member.setMemberId((String)session.getAttribute("memberId"));
		member.setMemberPw((String)session.getAttribute("memberPw"));
		return memberMapper.selectMember(member);
	}
	
	// 이메일로 아이디 찾기
	public void getMemberIdByEmail(String memberEmail) {
		// 아이디 찾기
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setTo(memberEmail);
		emailInfo.setSubject("아이디 찾기 메일");
		String id = memberMapper.selectMemberIdByEmail(memberEmail);
		String text = memberEmail+"회원님의 아이디는 "+id+"입니다.";
		emailInfo.setText(text);
		// 이메일 발송
		emailServicelmpl.sendSimpleMessage(emailInfo);		
	}
	
	// 아이디와 이메일로 비밀번호 찾기
	public void getMemberPassByIdAndEmail(Member member) {
		// 비밀번호 찾기
		String currentMemberPw = memberMapper.selectMemberPwByIdAndEmail(member);
		System.out.println("[cafe.jjdev.mall.service.MemberService.getMemberPassByIdAndEmail] currentMemberPw: "+currentMemberPw);
		// 비밀번호를 임시비밀변호로 변경
		UUID uuid = UUID.randomUUID();
		String memberPw = uuid.toString().replace("-", "");
		memberPw = memberPw.substring(0, 9);
		System.out.println("[cafe.jjdev.mall.service.MemberService.getMemberPassByIdAndEmail] memberPw: "+memberPw);
		Map<String, String> map = new HashMap<String, String>();
		map.put("currentMemberPw", currentMemberPw);
		map.put("memberPw", memberPw);
		map.put("memberId", member.getMemberId());
		memberMapper.updateMemberPw(map);
		
		// 임시 비밀번호를 이메일로 발송
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setTo(member.getMemberEmail());
		emailInfo.setSubject("비밀번호 찾기 메일");
		String text = member.getMemberId()+"회원님의 비밀번호는 "+memberPw+"입니다.";
		emailInfo.setText(text);
		// 이메일 발송
		emailServicelmpl.sendSimpleMessage(emailInfo);		
	}
	
	// 비밀번호만 수정
	public void ModifyMemberPw(Member member, String currentMemberPw) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", member.getMemberId());
		map.put("memberPw", member.getMemberPw());
		map.put("currentMemberPw", currentMemberPw);
		memberMapper.updateMemberPw(map);
	}
	
	// 비밀번호를 제외한 회원 수정
	public void modifyMember(Member member) {
		memberMapper.updateMember(member);
	}
	
	// 회원 탈퇴
	public void removeMember(Member member, HttpSession session) {
		// 회원 탈퇴 전 로그아웃	
		session.invalidate();
		// 회원 정보를 삭제
		memberMapper.deleteMember(member);
	}
}
